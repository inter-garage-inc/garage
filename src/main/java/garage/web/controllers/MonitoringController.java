package garage.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MonitoringController {
    @GetMapping(path = "/monitoring/heart_beat", produces = "application/json")
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
