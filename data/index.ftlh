<#import "layout.ftlh" as main>
<@main.layout>

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
                <div class="text-center">${day}<span class="patients"></span></div>
            </#list>
        </#if>
    </div>

    <style>
        .row {
            display: grid;
            grid-template-columns: repeat(7, 1fr);
            gap: 10px;
        }
        .row > div {
            background: #ccc;
            text-align: center;
            /*height: 100px;*/
            padding: 10px;
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
    </style>

</@main.layout>
