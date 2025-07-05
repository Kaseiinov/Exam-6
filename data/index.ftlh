<#import "layout.ftlh" as main>
<@main.layout>
    <div class="calendar mb-5">
        <div class="dayOfWeek">
            <div>Monday</div>
            <div>Tuesday</div>
            <div>Wednesday</div>
            <div>Thursday</div>
            <div>Friday</div>
            <div>Saturday</div>
            <div>Sunday</div>
        </div>

        <div class="row">
            <#if firstDayOfWeek??>
                <#list 1..(firstDayOfWeek - 1) as i>
                    <div class="empty"></div>
                </#list>
            </#if>

            <#if daysInMonth??>
            <#list daysInMonth as day>
            <div class="calendar_day <#if day == currentDay>today</#if>">
                <#if day?int gte currentDay?int>
                <a href="/day?day=${day}" class="day-link">
                    <#else>
                    <div class="day-content">
                        </#if>

                        <div class="text-center">
                            <h4>${day}</h4>

                            <#assign patientsForDay = patients?filter(p -> p.getDayInMonth() == day)>
                            <span class="badge bg-primary">Пациентов: ${patientsForDay?size}</span>

                            <#if day?int gte currentDay?int>
                                <#list patients as patient>
                                    <#if patient.dayInMonth == day>
                                        <h6 class="patient <#if patient.type?? && patient.type?lower_case?trim == 'первичный'>text-success<#else>text-danger</#if>">
                                            ${patient.fullName} ${patient.formattedTime} ${patient.type}
                                        </h6>
                                    </#if>
                                </#list>
                            </#if>
                        </div>

                        <#if day?int gte currentDay?int>
                </a>
                <#else>
            </div>
            </#if>
        </div>
        </#list>
        </#if>
    </div>
    </div>



    <style>
        .green {
            color: green;
        }

        .row {
            display: grid;
            grid-template-columns: repeat(7, 1fr);
            gap: 10px;
        }

        .calendar_day {
            position: relative;
            background: #ccc;
            text-align: center;
        }

        .day-link {
            display: block;
            padding: 10px;
            height: 100%;
            color: inherit;
            text-decoration: none;
        }

        .day-content {
            display: block;
            padding: 10px;
            height: 100%;
        }

        .day-link:hover {
            background-color: rgba(255,255,255,0.1);
        }

        .past-day {
            opacity: 0.6;
            cursor: default;
        }

        .dayOfWeek {
            display: grid;
            grid-template-columns: repeat(7, 1fr);
            gap: 10px;
        }

        .dayOfWeek > div {
            text-align: center;
            padding: 10px;
            font-weight: bold;
        }

        .empty {
            background: none;
            border: none;
        }

        .today {
            background: #ffa;
            font-weight: bold;
            border: 2px solid #f90;
        }

        .patient {
            font-size: 14px;
        }
    </style>

</@main.layout>