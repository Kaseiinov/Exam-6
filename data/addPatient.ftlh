<#import "layout.ftlh" as main>
<@main.layout>
    <main>
        <form action="/patient/save?date=${day}" method="post">


            <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Full name</label>
                <input name="fullName" type="text" class="form-control" id="exampleInputEmail1" >
            </div>
            <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label">Date of birth</label>
                <input name="dateOfBirth" type="date" class="form-control" id="exampleInputPassword1">
            </div>
            <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label">Recording time</label>
                <input name="recordingTime" type="time" class="form-control" id="exampleInputPassword1">
            </div>
            <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label">Anamnesis</label>
                <input type="text" name="anamnesis" class="form-control" id="exampleInputPassword1">
            </div>
            <div class="mb-3">
                <div class="form-element">
                    <label for="patientType">Тип приёма</label>
                    <select id="patientType" name="type" class="form-select" required>
                        <option value="" disabled selected>Выберите тип приёма</option>
                        <option value="Первичный">Первичный</option>
                        <option value="Вторичный">Вторичный</option>
                    </select>
                </div>
            </div>
            <div class="mb-3">
                <#if error??>
                    <span class="text-danger">${error}</span>
                </#if>
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>

        </form>
    </main>
</@main.layout>