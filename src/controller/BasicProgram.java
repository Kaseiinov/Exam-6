package controller;

import com.sun.net.httpserver.HttpExchange;
import server.BasicServer;
import server.Cookie;
import server.models.Patient;
import utils.JsonUtils;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class BasicProgram extends BasicServer {
    private JsonUtils utils = new JsonUtils();

    public BasicProgram(String host, int port) throws IOException {
        super(host, port);
        registerGet("/day", this::dayHandler);
        registerGet("/patient/add", this::addPatient);
    }

    private void addPatient(HttpExchange exchange) throws IOException {
        String query = getQuery(exchange);
        Map<String, String> params = Utils.parseUrlEncoded(query, "&");

        renderTemplate(exchange, "addPatient.ftlh", null);
    }

    private void dayHandler(HttpExchange exchange) throws FileNotFoundException {
        String query = getQuery(exchange);
        Map<String, String> params = Utils.parseUrlEncoded(query, "&");

        Map<String, Object> data = new HashMap<>();
        List<Patient> patients = utils.readPatients();
        List<Patient> filteredPatients = patients
                .stream()
                .filter(e -> e.getDayInMonth() == Integer.parseInt(params.get("day")))
                .sorted(Comparator.comparing(Patient::getRecordingTime))
                .toList();
        data.put("patients", filteredPatients);
        renderTemplate(exchange, "day.ftlh", data);
    }
}
