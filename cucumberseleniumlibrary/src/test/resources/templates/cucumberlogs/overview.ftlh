<!DOCTYPE html>
<html>
<head>
    <style media="all" type="text/css">
        body {
            font-family: Helvetica, sans-serif;
            font-size: 0.8em;
            color: black;
            padding: 6px;
            background: white;
        }
        h1, h2, h3{
            padding: 0.3em 0em 0em;
        }
        th, td {
            vertical-align: top;
            border: 1px solid #ccc;
            padding: 0.1em 0.3em;
        }
        th {
            background-color: #ddd;
            padding: 0.2em 0.3em;
        }
        td {
            padding: 0.2em;
        }
        td:not(:first-child) {
            text-align: center;
        }
        tr:hover {
            background: #f4f4f4;
            cursor: pointer;
        }
        table {
            table-layout: fixed;
            word-wrap: break-word;
            empty-cells: show;
            font-size: 1em;
            border-collapse: collapse;
        }
        .chart {
            border: 1px solid #ccc;
            border-collapse: collapse;
            background-color: none;
        }
        .passed {background-color:#5cb85c;}
        .skipped {background-color:silver;}
        .failed {background-color:#d9534f;font-weight:bold;}
        .undefined {background-color:gold;}
        .known {background-color:goldenrod;}

        div.header {
            display: block; text-align: center; 
            position: running(header);
        }
        div.footer {
            display: block; text-align: center;
            position: running(footer);
        }
        div.content {page-break-after: always;}
        @page {
             size: ${pdfPageSize};
             @top-center {
                content: "${title}";
                color: silver;
                font-size: 14px;
             }
             @top-right {
                content: date("dd MMM, yyyy hh:mm");
                color: silver;
                font-size: 8px;
             }
            @bottom-right {
                content: "Page " counter(page) " of " counter(pages) ;
                color: silver;
                font-size: 8px;
            }
        }
    </style>
<title>${title}</title>
<meta charset="UTF-8" />
</head>
  <body>
    <div class='header'></div>
    <div class='footer'></div>
    <#include "pie_chart">
    <#include "tables">
    <h1>Cucumber Framework Report Overview</h1>
      <h2>Overview Chart</h2>
        <table class="chart">
        <tr>
        <th class="chart">Features Status</th><th class="chart">Scenario Status</th>
        </tr>
        <tr class="chart">
        <td class="chart">
             <@drawPieChart 
                id="feature_status"
                width=450 height=300
                values=[overallStats.getFeaturesPassed(),
                        overallStats.getFeaturesFailed(),
                        overallStats.getFeaturesUndefined(),
                        overallStats.getFeaturesKnown()]
                labels=["Passed", "Failed", "Undefined", "Known"]
                colors=["#5cb85c", "#d9534f", "silver", "gold"]
                shadowColors=["darkgreen", "darkred", "darkgray", "goldenrod"]
                pieThickness=20
                shift=2 />
        </td>
        <td class="chart">
             <@drawPieChart 
                id="scenario_status"
                width=450 height=300
                values=[overallStats.getScenariosPassed(),
                        overallStats.getScenariosFailed(),
                        overallStats.getScenariosUndefined(),
                        overallStats.getScenariosKnown()]
                labels=["Passed", "Failed", "Undefined", "Known"]
                colors=["#5cb85c", "#d9534f", "silver", "gold"]
                shadowColors=["darkgreen", "darkred", "darkgray", "goldenrod"]
                pieThickness=20
                shift=2 />
        </td>
        </tr>
        </table>

    <h2>Summary</h2>

    <@drawOverviewStatsTable stats=overallStats />
        
    <h2>Features Status</h2>
        
    <table>
        <tr>
            <th>Feature Name</th>
            <th>Passed</th>
            <th>Failed</th>
            <th>Known</th>
            <th>Undefined</th>
            <th>Total</th>
            <th>Duration</th>
            <th>Status</th>
        </tr>
    <#list features as feature>
        <#if feature.featureName?has_content>
        <tr>
            <td>${feature.featureName}</td>
            <td>${feature.stats.scenariosPassed}</td>
            <td>${feature.stats.scenariosFailed}</td>
            <td>${feature.stats.scenariosKnown}</td>
            <td>${feature.stats.scenariosUndefined}</td>
            <td>${feature.stats.getScenariosTotal()}</td>
            <td>${feature.duration}s</td>
            <td class="${feature.status}" >${feature.status}</td>
        </tr>   
        </#if>
    </#list>

    </table>
        
    <h2>Scenario Status</h2>

    <table>
        <tr>
            <th>Feature Name</th>
            <th>Scenario</th>
            <th>Passed</th>
            <th>Failed</th>
            <th>Known</th>
            <th>Undefined</th>
            <th>Total</th>
            <th>Retries</th>
            <th>Duration</th>
            <th>Status</th>
        </tr>
    <#list scenarios as scenario>
        <tr>
            <td>${scenario.featureName}</td>
            <td>${scenario.scenarioName}</td>
            <td>${scenario.stats.stepsPassed}</td>
            <td>${scenario.stats.stepsFailed}</td>
            <td>${scenario.stats.stepsKnown}</td>
            <td>${scenario.stats.stepsUndefined}</td>
            <td>${scenario.stats.getStepsTotal()}</td>
            <td>${scenario.retries}</td>
            <td>${scenario.duration}s</td>
            <td class="${scenario.status}">${scenario.status}</td>
        </tr>   
    </#list>
    </table>    
    </body>
</html>