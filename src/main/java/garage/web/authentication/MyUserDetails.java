package garage.web.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import garage.core.entity.User;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.ArrayList;

@Getter
public class MyUserDetails extends org.springframework.security.core.userdetails.User {
    private User user;

    public MyUserDetails(User user) {
        super(user.getUsername(), user.getPassword(), new ArrayList<>());
        this.user = user;
    }


    @SneakyThrows
    public String getAsString() {
        var mapper = new ObjectMapper();
        return mapper.writeValueAsString(user);
    }
}
