package com.shopping.userDataModule1.controller;

import com.shopping.userDataModule1.exception.InvalidCredentials;
import com.shopping.userDataModule1.exception.UsernameNotFoundException1;
import com.shopping.userDataModule1.jwtRequest.JwtRequest;
import com.shopping.userDataModule1.model.OtpRequest;
import com.shopping.userDataModule1.model.UserModel;
import com.shopping.userDataModule1.model.frogotPasswordModel;
import com.shopping.userDataModule1.response.OtpResponse;
import com.shopping.userDataModule1.response.Response;
import com.shopping.userDataModule1.response.TokenResponse;
import com.shopping.userDataModule1.service.OtpService;
import com.shopping.userDataModule1.service.UserDetailService;
import com.shopping.userDataModule1.service.UserDetailServiceImpl;
import com.shopping.userDataModule1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    private Integer id = 1001;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OtpService otpService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody UserModel user){
        Response Response = new Response();
        //UserModel userModel =new UserModel(user);
        Integer count = this.userDetailService.getUserCount();
        Integer userId=0;
        UserModel user2=this.userDetailService.findByUserName(user.getUsername());
        if(user2==null) {
            //System.out.println("test "+userModelsList.get(userModelsList.size() - 1));
            if(count>0) {
                List userModelsList=this.userDetailService.getUserIds();
                Collections.sort(userModelsList);
                userId = (Integer) userModelsList.get(userModelsList.size() - 1) + 1;
            }else
            {
                userId = 1001;
            }
            user.setUserId(userId);
            UserModel user1 = this.userDetailService.addUser(user);
            if (user1 != null) {
                Response.setStatusCode(0);
                Response.setResponseMessage("Registration Successfull");
                ResponseEntity.ok().body(Response);
            }else{
                Response.setStatusCode(400);
                Response.setResponseMessage("Somthing Went Wrong");
                ResponseEntity.ok().body(Response);
            }
        }else {
            Response.setStatusCode(101);
            Response.setResponseMessage("User Already Exists");
            ResponseEntity.ok().body(Response);
        }
        id=1001;
        return ResponseEntity.ok(Response);
    }

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> getToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        UserModel user =this.userDetailService.findByUserName(jwtRequest.getUsername());
        if(user==null){
            throw new UsernameNotFoundException1("userNotFound");
        }if(!user.getPassword().equals(jwtRequest.getPassword())){
            throw new InvalidCredentials("invalid");
        }
       try {
           this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                   jwtRequest.getUsername(),jwtRequest.getPassword()
           ));
       }catch(UsernameNotFoundException e){
           System.out.println("not");
           throw new UsernameNotFoundException("bad credntial");
       }
       UserDetails userDetails=this.userDetailServiceImpl.loadUserByUsername(jwtRequest.getUsername());
       System.out.print("user =>"+userDetails);
       String token = this.jwtUtil.generateToken(userDetails);
        System.out.println(token);
       TokenResponse tokenResponse=new TokenResponse();
       tokenResponse.setToken(token);
       tokenResponse.setResponseMessage("Success");
       tokenResponse.setStatusCode(0);
       return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/otp")
    public ResponseEntity<OtpResponse> getOtp(@RequestBody OtpRequest otpRequest) throws Exception {
        System.out.println(otpRequest);
        if(otpRequest.getFlag().equals("G")){
            OtpResponse otpresp = new OtpResponse();
            UserModel user =this.userDetailService.findUserByEmail(otpRequest.getEmail());
            if(user==null){
                otpresp.setFlag("USER_NOT_FOUND");
                otpresp.setStatus("User Not Found");
                otpresp.setStatusCode("404");
                otpresp.setMessage("User not Found");
                return ResponseEntity.status(200).body(otpresp);
            }
            int otp = otpService.generateOTP(user.getUsername());
//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//            LocalDateTime now = LocalDateTime.now();
//            System.out.println(dtf.format(now));
//            Calendar now = Calendar.getInstance();
//            Calendar expireAt = Calendar.getInstance();
//            expireAt.add(Calendar.MINUTE,4);
            // Adding 10 mins using Date constructor.
            Calendar date = Calendar.getInstance();
            long timeInSecs = date.getTimeInMillis();
            Date afterAdding4Mins = new Date(timeInSecs + (4 * 60 * 1000));
            otpresp.setOTP(Integer.toString(otp));
            otpresp.setCreatedAt(date.getTime().toString());
            otpresp.setExpiresAt(afterAdding4Mins.toString());
            otpresp.setFlag("GENERATED");
            otpresp.setStatusCode("101");
            otpresp.setStatus("success");
            otpresp.setMessage("OTP Generation Successfull");
            System.out.println(otpresp);
            return ResponseEntity.status(200).body(otpresp);
        }else if(otpRequest.getFlag().equals("V")){
            UserModel user =this.userDetailService.findUserByEmail(otpRequest.getEmail());
            int otp = Integer.parseInt(otpRequest.getOtp());
            OtpResponse otpresp = new OtpResponse();
            if(user==null){
                otpresp.setFlag("USER_NOT_FOUND");
                otpresp.setStatus("User Not Found");
                otpresp.setStatusCode("404");
                otpresp.setMessage("User not Found");
                return ResponseEntity.status(200).body(otpresp);
            }
            if(otp>0){
                int serverOtp = otpService.getOtp(user.getUsername());
                if(serverOtp>=0){
                    if(otp==serverOtp){
                        //otpresp.setOTP("");
                        otpresp.setFlag("VERIFIED");
                        otpresp.setStatus("success");
                        otpresp.setStatusCode("101");
                        otpresp.setMessage("OTP Verification Successfull");
                        otpService.clearOTP(user.getUsername());
                    }else{
                        otpresp.setFlag("INVALID");
                        otpresp.setStatus("invalid");
                        otpresp.setStatusCode("103");
                        otpresp.setMessage("OTP Not Valid");
                    }
                }else{
                    otpresp.setFlag("NOT_FOUND");
                    otpresp.setStatus("not found");
                    otpresp.setStatusCode("404");
                    otpresp.setMessage("OTP Not Generated");
                }
            }else{
                otpresp.setFlag("FAILED");
                otpresp.setStatus("failed");
                otpresp.setStatusCode("102");
                otpresp.setMessage("OTP Not Received");
            }
            return ResponseEntity.status(200).body(otpresp);
        }
       return null;
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<Response> forgotPassWord(@Valid @RequestBody frogotPasswordModel forgotreq){
        System.out.println(forgotreq);
        Response resp = new Response();
        if(forgotreq.getPassword().equals(forgotreq.getConfPassword())) {
	        UserModel user = this.userDetailService.findUserByEmail(forgotreq.getEmail());
	        if(user!=null) {
	        	user.setPassword(forgotreq.getPassword());
	        	UserModel returnData = this.userDetailService.addUser(user);
	        	if(returnData!=null) {
	        		resp.setResponseMessage("User Details Updated Successfully");
		        	resp.setStatusCode(0);
	        	}else {
	        		resp.setResponseMessage("Something went Wrong!");
		        	resp.setStatusCode(100);
	        	}
	        }else {
	        	resp.setResponseMessage("User Data not found");
	        	resp.setStatusCode(404);
	        }
        }else {
        	resp.setResponseMessage("Password and confirm Password are not same!");
        	resp.setStatusCode(102);
        }
        return ResponseEntity.ok().body(resp);
    }

}
