package org.helmo.HolyD.controlers;

import org.helmo.HolyD.controlers.swagger.UserControlerSwagger;
import org.helmo.HolyD.models.reponses.NbrUserAndNbrUserInHoliday;
import org.helmo.HolyD.models.reponses.User;
import org.helmo.HolyD.models.requests.UserSignIn;
import org.helmo.HolyD.models.requests.UserSignInWithProvider;
import org.helmo.HolyD.models.requests.UserSignUp;
import org.helmo.HolyD.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/user")
public class UserControler implements UserControlerSwagger {

    private final UserService userService;

    public UserControler(UserService userService) {
        this.userService = userService;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User signUp(@Valid @RequestBody UserSignUp user){
        return userService.signUp(user);
    }
    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User signIn(@Valid @RequestBody UserSignIn user){
        return userService.signIn(user);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/signinWithProvider", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User signInWithProvider(@Valid @RequestBody UserSignInWithProvider user){
        return userService.signInWithProvider(user);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/byHoliday", produces = MediaType.APPLICATION_JSON_VALUE)
    public NbrUserAndNbrUserInHoliday nbrUserAndNbrUserInHolidayForADate(@Valid @NotNull @RequestParam OffsetDateTime dateTime){
        return new NbrUserAndNbrUserInHoliday(userService.getNbrOfUser(), userService.getNbrOfUserInHolidayForADate(dateTime));
    }

}
