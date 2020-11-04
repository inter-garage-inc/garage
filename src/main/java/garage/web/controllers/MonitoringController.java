package garage.controller.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitoringController {
    @RequestMapping(method = RequestMethod.GET, path = "/monitoring/heart_beat", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public HeartBeat heartBeat() {
        return HeartBeat.OK;
    }

    public static class HeartBeat {
        public static final HeartBeat OK = new HeartBeat("ok");
        private final String status;

        private HeartBeat(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }
}
