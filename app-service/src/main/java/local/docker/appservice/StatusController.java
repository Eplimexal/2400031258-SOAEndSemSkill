package local.docker.appservice;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
    private final JdbcTemplate jdbc;

    public StatusController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping("/api/data")
    public Map<String, Object> data() {
        Map<String, Object> row = jdbc.queryForMap(
                "select current_database() as database, current_timestamp as server_time");
        return Map.of("status", "ok", "data", row);
    }
}
