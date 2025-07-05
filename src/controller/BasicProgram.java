package controller;

import com.sun.net.httpserver.HttpExchange;
import server.BasicServer;
import server.Cookie;
import server.models.Patient;
import utils.JsonUtils;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class BasicProgram extends BasicServer {
    private JsonUtils utils = new JsonUtils();

    public BasicProgram(String host, int port) throws IOException {
        super(host, port);
        registerGet("/day", this::dayHandler);
        registerGet("/patient/add", this::addPatient);
        registerPost("/patient/save", this::savePatient);
        registerPost("/patient/delete", this::deleteHandler);
    }

    private void deleteHandler(HttpExchange exchange) throws IOException {
        String query = getQuery(exchange);
        Map<String, String> params = Utils.parseUrlEncoded(query, "&");

        String name = params.get("name");

        utils.removePatientByName(name);
        redirect(exchange, "/day?day=" + params.get("date"));
    }

    private void savePatient(HttpExchange exchange) throws IOException {
        try {
            String query = getQuery(exchange);
            Map<String, String> params = Utils.parseUrlEncoded(query, "&");
            String raw = getBody(exchange);
            Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");



            if(parsed.get("fullName") == null || parsed.get("fullName").isBlank()) {
                redirect(exchange, "/patient/add?error=User name cannot be empty&date=" + params.get("date"));
                return;
            }


            if(LocalDate.parse(parsed.get("dateOfBirth")).isAfter(LocalDate.now()) || parsed.get("dateOfBirth") == null) {
                redirect(exchange, "/patient/add?error=Date of birth cannot be after current date&date=" + params.get("date"));
                return;
            }

            if(params.get("anamnesis") != null){
                redirect(exchange, "/patient/add?error=Anamnesis cannot be empty&date=" + params.get("date"));
                return;
            }

            LocalDateTime dateTime = LocalDateTime.parse(params.get("date"),
                    DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            String timeStr = parsed.get("recordingTime");
            String[] timeParts = timeStr.split(":");
            int hours = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);


            Patient patient = new Patient();
            patient.setFullName(parsed.get("fullName"));
            patient.setDateOfBirth(LocalDate.parse(parsed.get("dateOfBirth")));
            patient.setAnamnesis(parsed.get("anamnesis"));
            patient.setType(parsed.get("type"));

            patient.setRecordingTime(LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), hours, minutes
            ));

            utils.addPatientToJson(patient);
            redirect(exchange, "/day?day=" + dateTime.getDayOfMonth());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addPatient(HttpExchange exchange) throws IOException {
        String query = getQuery(exchange);
        Map<String, String> params = Utils.parseUrlEncoded(query, "&");
        Map<String, String> data = new HashMap<>();
        data.put("day", params.get("date"));
        data.put("error", params.get("error"));

        renderTemplate(exchange, "addPatient.ftlh", data);
    }

    private void dayHandler(HttpExchange exchange) throws FileNotFoundException {
        String query = getQuery(exchange);
        Map<String, String> params = Utils.parseUrlEncoded(query, "&");
        LocalDateTime dateTime = LocalDateTime.of(2025, 07, Integer.parseInt(params.get("day")), 0, 0);

        Map<String, Object> data = new HashMap<>();
        List<Patient> patients = utils.readPatients();
        List<Patient> filteredPatients = patients
                .stream()
                .filter(e -> e.getDayInMonth() == Integer.parseInt(params.get("day")))
                .sorted(Comparator.comparing(Patient::getRecordingTime))
                .toList();
        data.put("patients", filteredPatients);
        data.put("dateTime", dateTime);
        renderTemplate(exchange, "day.ftlh", data);
    }
}
