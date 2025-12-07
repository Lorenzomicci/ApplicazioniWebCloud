package com.progetto.web.cloud.progetto.repository;

import com.progetto.web.cloud.progetto.model.Hackathon;
import com.progetto.web.cloud.progetto.model.HackathonTrack;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HackathonRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public HackathonRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Hackathon> findAll(String status, String search) {
        StringBuilder sql = new StringBuilder("SELECT * FROM hackathons WHERE 1=1");
        MapSqlParameterSource params = new MapSqlParameterSource();

        if (status != null && !status.isBlank()) {
            sql.append(" AND status = :status");
            params.addValue("status", status);
        }

        if (search != null && !search.isBlank()) {
            sql.append(" AND (LOWER(name) LIKE :search OR LOWER(description) LIKE :search)");
            params.addValue("search", "%" + search.toLowerCase() + "%");
        }

        sql.append(" ORDER BY created_at DESC");
        return jdbcTemplate.query(sql.toString(), params, new HackathonRowMapper());
    }

    public Optional<Hackathon> findById(Long id) {
        String sql = "SELECT * FROM hackathons WHERE id = :id";
        List<Hackathon> results = jdbcTemplate.query(sql, Map.of("id", id), new HackathonRowMapper());
        return results.stream().findFirst();
    }

    public Hackathon save(Hackathon hackathon) {
        String sql = """
                INSERT INTO hackathons (
                    name, slug, description, location, status,
                    registration_start_at, registration_end_at,
                    event_start_at, event_end_at, submission_deadline,
                    min_team_size, max_team_size, max_participants
                ) VALUES (
                    :name, :slug, :description, :location, :status,
                    :registrationStartAt, :registrationEndAt,
                    :eventStartAt, :eventEndAt, :submissionDeadline,
                    :minTeamSize, :maxTeamSize, :maxParticipants
                )
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", hackathon.name())
                .addValue("slug", hackathon.slug())
                .addValue("description", hackathon.description())
                .addValue("location", hackathon.location())
                .addValue("status", hackathon.status())
                .addValue("registrationStartAt", hackathon.registrationStartAt())
                .addValue("registrationEndAt", hackathon.registrationEndAt())
                .addValue("eventStartAt", hackathon.eventStartAt())
                .addValue("eventEndAt", hackathon.eventEndAt())
                .addValue("submissionDeadline", hackathon.submissionDeadline())
                .addValue("minTeamSize", hackathon.minTeamSize())
                .addValue("maxTeamSize", hackathon.maxTeamSize())
                .addValue("maxParticipants", hackathon.maxParticipants());

        jdbcTemplate.update(sql, params);
        Long id = jdbcTemplate.getJdbcTemplate().queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return new Hackathon(id, hackathon.name(), hackathon.slug(), hackathon.description(), hackathon.location(),
                hackathon.status(), hackathon.registrationStartAt(), hackathon.registrationEndAt(),
                hackathon.eventStartAt(), hackathon.eventEndAt(), hackathon.submissionDeadline(),
                hackathon.minTeamSize(), hackathon.maxTeamSize(), hackathon.maxParticipants());
    }

    public int update(Long id, Hackathon hackathon) {
        String sql = """
                UPDATE hackathons SET
                    name = :name,
                    slug = :slug,
                    description = :description,
                    location = :location,
                    status = :status,
                    registration_start_at = :registrationStartAt,
                    registration_end_at = :registrationEndAt,
                    event_start_at = :eventStartAt,
                    event_end_at = :eventEndAt,
                    submission_deadline = :submissionDeadline,
                    min_team_size = :minTeamSize,
                    max_team_size = :maxTeamSize,
                    max_participants = :maxParticipants
                WHERE id = :id
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", hackathon.name())
                .addValue("slug", hackathon.slug())
                .addValue("description", hackathon.description())
                .addValue("location", hackathon.location())
                .addValue("status", hackathon.status())
                .addValue("registrationStartAt", hackathon.registrationStartAt())
                .addValue("registrationEndAt", hackathon.registrationEndAt())
                .addValue("eventStartAt", hackathon.eventStartAt())
                .addValue("eventEndAt", hackathon.eventEndAt())
                .addValue("submissionDeadline", hackathon.submissionDeadline())
                .addValue("minTeamSize", hackathon.minTeamSize())
                .addValue("maxTeamSize", hackathon.maxTeamSize())
                .addValue("maxParticipants", hackathon.maxParticipants());
        return jdbcTemplate.update(sql, params);
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM hackathons WHERE id = :id", Map.of("id", id));
    }

    public List<HackathonTrack> findTracks(Long hackathonId) {
        String sql = "SELECT * FROM hackathon_tracks WHERE hackathon_id = :hackathonId ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, Map.of("hackathonId", hackathonId), new TrackRowMapper());
    }

    public HackathonTrack insertTrack(Long hackathonId, HackathonTrack track) {
        String sql = """
                INSERT INTO hackathon_tracks (hackathon_id, name, description)
                VALUES (:hackathonId, :name, :description)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("hackathonId", hackathonId)
                .addValue("name", track.name())
                .addValue("description", track.description());
        jdbcTemplate.update(sql, params);
        Long id = jdbcTemplate.getJdbcTemplate().queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return new HackathonTrack(id, hackathonId, track.name(), track.description(), LocalDateTime.now(), LocalDateTime.now());
    }

    public int updateTrack(Long trackId, HackathonTrack track) {
        String sql = """
                UPDATE hackathon_tracks
                SET name = :name, description = :description
                WHERE id = :trackId
                """;
        return jdbcTemplate.update(sql, Map.of(
                "name", track.name(),
                "description", track.description(),
                "trackId", trackId
        ));
    }

    public int deleteTrack(Long trackId) {
        return jdbcTemplate.update("DELETE FROM hackathon_tracks WHERE id = :id", Map.of("id", trackId));
    }

    private static class HackathonRowMapper implements RowMapper<Hackathon> {
        @Override
        public Hackathon mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Hackathon(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("slug"),
                    rs.getString("description"),
                    rs.getString("location"),
                    rs.getString("status"),
                    getDateTime(rs, "registration_start_at"),
                    getDateTime(rs, "registration_end_at"),
                    getDateTime(rs, "event_start_at"),
                    getDateTime(rs, "event_end_at"),
                    getDateTime(rs, "submission_deadline"),
                    rs.getInt("min_team_size"),
                    rs.getInt("max_team_size"),
                    (Integer) rs.getObject("max_participants")
            );
        }

        private LocalDateTime getDateTime(ResultSet rs, String column) throws SQLException {
            var timestamp = rs.getTimestamp(column);
            return timestamp != null ? timestamp.toLocalDateTime() : null;
        }
    }

    private static class TrackRowMapper implements RowMapper<HackathonTrack> {
        @Override
        public HackathonTrack mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new HackathonTrack(
                    rs.getLong("id"),
                    rs.getLong("hackathon_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    getDateTime(rs, "created_at"),
                    getDateTime(rs, "updated_at")
            );
        }

        private LocalDateTime getDateTime(ResultSet rs, String column) throws SQLException {
            var timestamp = rs.getTimestamp(column);
            return timestamp != null ? timestamp.toLocalDateTime() : null;
        }
    }
}
