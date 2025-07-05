<#import "layout.ftlh" as main>
<@main.layout>
    <div class="day">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Full name</th>
                <th scope="col">Date of birth</th>
                <th scope="col">Recording time</th>
                <th scope="col">Type</th>
                <th scope="col">Anamnesis</th>
            </tr>
            </thead>
            <tbody>
            <#if patients??>
                <#list patients as patient>
                    <tr>
                        <td>${patient.fullName}</td>
                        <td>${patient.formattedDateOfBirth}</td>
                        <td>${patient.formattedTime}</td>
                        <td>${patient.type}</td>
                        <td>${patient.anamnesis}</td>
                    </tr>
                </#list>
            </#if>

            </tbody>
        </table>
        <#if patients?? && patients?size gt 0>
            <a href="/patient/add?date=${patients[0].recordingTime}">Add new patient</a>
        </#if>
    </div>
</@main.layout>