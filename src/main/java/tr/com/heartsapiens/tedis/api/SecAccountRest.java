package tr.com.heartsapiens.tedis.api;

import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tr.com.heartsapiens.tedis.dto.SecUserDto;
import tr.com.heartsapiens.tedis.service.SecUserService;
import tr.com.heartsapiens.tedis.service.impl.SecUserServiceImpl;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value =  RestPaths.AccountRest.Path)
public class SecAccountRest {

    @Autowired
    private SecUserService secUserService;

    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        return request.getRemoteUser();
    }
    @GetMapping()
    public ResponseEntity<SecUserDto> getAccount(HttpServletRequest request) {

        String remoteUser = request.getRemoteUser();
        if(StringUtils.isEmpty(remoteUser)) {
            return ResponseEntity.ok().build();
        }

        SecUserDto userDto = (SecUserDto) secUserService.loadUserByUsername(remoteUser);
        return ResponseEntity.ok(userDto);
    }


}
