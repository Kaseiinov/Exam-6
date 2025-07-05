package server.models;

import com.google.gson.annotations.Expose;
import utils.JsonUtils;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Patient {

    @Expose
    private String fullName;
    @Expose
    private LocalDate dateOfBirth;
    @Expose
    private LocalDateTime recordingTime;
    @Expose
    private String type;
    @Expose
    private String anamnesis;


    public Patient(String fullName, LocalDate dateOfBirth, LocalDateTime recordingTime, String type, String anamnesis) throws FileNotFoundException {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.recordingTime = recordingTime;
        this.type = type;
        this.anamnesis = anamnesis;

    }

    public Patient()  {

    }

    public String getFormattedDateOfBirth() {
        return dateOfBirth != null ?
                dateOfBirth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : "";
    }

    public String getFormattedDateTime() {
        return recordingTime != null ?
                recordingTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) : "";
    }

    public int getDayInMonth() {
        return recordingTime.getDayOfMonth();
    }

    public String getFormattedTime() {
        return recordingTime != null ? recordingTime.toLocalTime().toString() : "";
    }

    public LocalDateTime getRecordingTime() {
        return recordingTime;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setRecordingTime(LocalDateTime recordingTime) {
        this.recordingTime = recordingTime;
    }

    public String getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(String anamnesis) {
        this.anamnesis = anamnesis;
    }


}
