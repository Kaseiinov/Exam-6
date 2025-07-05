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
                <th scope="col">Actions</th>
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

                        <td>
                            <form action="/patient/delete?name=${patient.fullName}&date=${patient.dayInMonth}" method="post">
                                <button class="btn btn-outline-info">
                                  Delete
                                </button>
                            </form>
                        </td>
                    </tr>
                </#list>
            </#if>

            </tbody>
        </table>
        <#if patients?? && patients?size gt 0>
            <a class="btn btn-outline-info" href="/patient/add?date=${patients[0].recordingTime}">Add new patient</a>
            <#else>
                <a class="btn btn-outline-info" href="/patient/add?date=${dateTime}">Add new patient</a>
        </#if>
    </div>
</@main.layout>