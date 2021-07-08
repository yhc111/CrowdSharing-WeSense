var BuAct1, DalLif1, CiTra1, EnTai1, PuSaf1;
var BuAct2, DalLif2, CiTra2, EnTai2, PuSaf2;

function getUnfinishedTaskNumByClass(){
		$.ajax({
			type:"get",
			url:"http://localhost:8080/task/getUnfinishedTaskNumByClass",
			async:false,
			dataType:"json",
			success:function(result){
				BuAct1 = result.BuAct;
				DalLif1 = result.DalLif;
				CiTra1 = result.CiTra;
				EnTai1 = result.EnTai;
				PuSaf1 = result.PuSaf;
			},
			error:function(){
				BuAct1 = 0;
				DalLif1 = 0;
				CiTra1 = 0;
				EnTai1 = 0;
				PuSaf1 = 0;
			}
		});
}

function getFinishedTaskNumByClass(){
		$.ajax({
			type:"get",
			url:"http://localhost:8080/task/getFinishedTaskNumByClass",
			async:false,
			dataType:"json",
			success:function(result){
				BuAct2 = result.BuAct;
				DalLif2 = result.DalLif;
				CiTra2 = result.CiTra;
				EnTai2 = result.EnTai;
				PuSaf2 = result.PuSaf;
			},
			error:function(){
				BuAct2 = 0;
				DalLif2 = 0;
				CiTra2 = 0;
				EnTai2 = 0;
				PuSaf2 = 0;
			}
		});
}

$(document).ready(function () {

    'use strict';

    Chart.defaults.global.defaultFontColor = '#75787c';

    getUnfinishedTaskNumByClass();
    getFinishedTaskNumByClass();

    // ------------------------------------------------------- //
    // Line Chart
    // ------------------------------------------------------ //
    var legendState = true;
    if ($(window).outerWidth() < 576) {
        legendState = false;
    }

    var LINECHART = $('#lineCahrt');
    var myLineChart = new Chart(LINECHART, {
        type: 'line',
        options: {
            scales: {
                xAxes: [{
                    display: true,
                    gridLines: {
                        display: false
                    }
                }],
                yAxes: [{
                    display: true,
                    gridLines: {
                        display: false
                    }
                }]
            },
            legend: {
                display: legendState
            }
        },
        data: {
            labels: ["", "9 days ago", "8 days ago", "7 days ago", "6 days ago", "5 days ago", "4 days ago", "3 days ago", "2 days ago", "1 days ago",],
            datasets: [
                {
                    label: "Unfinished Task",
                    fill: true,
                    lineTension: 0.2,
                    backgroundColor: "transparent",
                    borderColor: '#76EE00',
                    pointBorderColor: '#76EE00',
                    pointHoverBackgroundColor: '#76EE00',
                    borderCapStyle: 'butt',
                    borderDash: [],
                    borderDashOffset: 0.0,
                    borderJoinStyle: 'miter',
                    borderWidth: 2,
                    pointBackgroundColor: "#fff",
                    pointBorderWidth: 5,
                    pointHoverRadius: 5,
                    pointHoverBorderColor: "#fff",
                    pointHoverBorderWidth: 2,
                    pointRadius: 1,
                    pointHitRadius: 0,
                    data: [20, 27, 20, 35, 30, 40, 33, 25, 39, 40],
                    spanGaps: false
                },
                {
                    label: "Finished Task",
                    fill: true,
                    lineTension: 0.2,
                    backgroundColor: "transparent",
                    borderColor: "#D1EEEE",
                    pointBorderColor: '#D1EEEE',
                    pointHoverBackgroundColor: "#D1EEEE",
                    borderCapStyle: 'butt',
                    borderDash: [],
                    borderDashOffset: 0.0,
                    borderJoinStyle: 'miter',
                    borderWidth: 2,
                    pointBackgroundColor: "#fff",
                    pointBorderWidth: 5,
                    pointHoverRadius: 5,
                    pointHoverBorderColor: "#fff",
                    pointHoverBorderWidth: 2,
                    pointRadius: 1,
                    pointHitRadius: 10,
                    data: [25, 17, 28, 25, 33, 27, 30, 33, 27, 36],
                    spanGaps: false
                }
            ]
        }
    });



    // ------------------------------------------------------- //
    // Bar Chart
    // ------------------------------------------------------ //
    var BARCHARTEXMPLE    = $('#barChartExample1');

    Chart.defaults.global.defaultFontSize = 10;

    var barChartExample = new Chart(BARCHARTEXMPLE, {
        type: 'bar',
        options: {
            scales: {
                xAxes: [{
                    display: true,
                    gridLines: {
                        //color: '#eee'
                        display: false
                    }
                }],
                yAxes: [{
                	ticks: {
                		min: 0
                	},
                    display: true,
                    gridLines: {
                        //color: '#eee'
                        display: false
                    }
                }]
            },
        },
        data: {
            labels: ["Public Safety", "Daily Life", "City Traffic", "Business Activities", "Entertainment"],
            datasets: [
                {
                    label: "Unfinished Task",
                    backgroundColor: [
                        "#1C86EE",
                        "#1C86EE",
                        "#1C86EE",
                        "#1C86EE",
                        "#1C86EE"
                    ],
                    hoverBackgroundColor: [
                        "#1C86EE",
                        "#1C86EE",
                        "#1C86EE",
                        "#1C86EE",
                        "#1C86EE"
                    ],
                    borderColor: [
                        "#1C86EE",
                        "#1C86EE",
                        "#1C86EE",
                        "#1C86EE",
                        "#1C86EE"
                    ],
                    borderWidth: 1,
                    data: [PuSaf1, DalLif1, CiTra1, BuAct1, EnTai1],
                }
            ]
        }
    });


    // ------------------------------------------------------- //
    // Line Chart 1
    // ------------------------------------------------------ //
    var LINECHART1 = $('#lineChart1');
    var myLineChart = new Chart(LINECHART1, {
        type: 'line',
        options: {
            scales: {
                xAxes: [{
                    display: true,
                    gridLines: {
                        display: false
                    }
                }],
                yAxes: [{
                    ticks: {
                    },
                    display: true,
                    gridLines: {
                        display: false
                    }
                }]
            },
            legend: {
                display: true
            }
        },
        data: {
            labels: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
            datasets: [
                {
                    label: "Task Publisher",
                    fill: true,
                    lineTension: 0.3,
                    backgroundColor: "transparent",
                    borderColor: '#EF8C99',
                    pointBorderColor: '#EF8C99',
                    pointHoverBackgroundColor: '#EF8C99',
                    borderCapStyle: 'butt',
                    borderDash: [],
                    borderDashOffset: 0.0,
                    borderJoinStyle: 'miter',
                    borderWidth: 2,
                    pointBackgroundColor: "#EF8C99",
                    pointBorderWidth: 2,
                    pointHoverRadius: 4,
                    pointHoverBorderColor: "#fff",
                    pointHoverBorderWidth: 0,
                    pointRadius: 1,
                    pointHitRadius: 0,
                    data: [20, 21, 25, 22, 24, 18, 20, 23, 19, 22, 25, 19],
                    spanGaps: false
                },
                {
                    label: "Worker",
                    fill: true,
                    lineTension: 0.3,
                    backgroundColor: "transparent",
                    borderColor: 'rgba(238, 139, 152, 0.24)',
                    pointBorderColor: 'rgba(238, 139, 152, 0.24)',
                    pointHoverBackgroundColor: 'rgba(238, 139, 152, 0.24)',
                    borderCapStyle: 'butt',
                    borderDash: [],
                    borderDashOffset: 0.0,
                    borderJoinStyle: 'miter',
                    borderWidth: 2,
                    pointBackgroundColor: "rgba(238, 139, 152, 0.24)",
                    pointBorderWidth: 2,
                    pointHoverRadius: 4,
                    pointHoverBorderColor: "#fff",
                    pointHoverBorderWidth: 0,
                    pointRadius: 1,
                    pointHitRadius: 0,
                    data: [24, 20, 23, 19, 22, 20, 25, 21, 23, 19, 21, 23],
                    spanGaps: false
                }
            ]
        }
    });


    // ------------------------------------------------------- //
    // Bar Chart
    // ------------------------------------------------------ //
    var BARCHARTEXMPLE = $('#barChartExample2');
    var barChartExample = new Chart(BARCHARTEXMPLE, {
        type: 'bar',
        options: {
            scales: {
                xAxes: [{
                    display: true,
                    gridLines: {
                    	display: false
                        //color: '#eee'
                    }
                }],
                yAxes: [{
                	ticks: {
                		min: 0
                	},
                    display: true,
                    gridLines: {
                        display: false
                        //color: '#eee'
                    }
                }]
            },
        },
        data: {
            labels: ["Public Safety", "Daily Life", "City Traffic", "Business Activities", "Entertainment"],
            datasets: [
                {
                    label: "Finished Task",
                    backgroundColor: [
                        "#FF82AB",
                        "#FF82AB",
                        "#FF82AB",
                        "#FF82AB",
                        "#FF82AB"
                    ],
                    hoverBackgroundColor: [
                        "#FF82AB",
                        "#FF82AB",
                        "#FF82AB",
                        "#FF82AB",
                        "#FF82AB"
                    ],
                    borderColor: [
                        "#FF82AB",
                        "#FF82AB",
                        "#FF82AB",
                        "#FF82AB",
                        "#FF82AB"
                    ],
                    borderWidth: 1,
                    data: [PuSaf2, DalLif2, CiTra2, BuAct2, EnTai2],
                }
            ]
        }
    });


    // ------------------------------------------------------- //
    // Pie Chart 1
    // ------------------------------------------------------ //
    var PIECHART = $('#pieChartHome1');
    var myPieChart = new Chart(PIECHART, {
        type: 'doughnut',
        options: {
            cutoutPercentage: 90,
            legend: {
                display: false
            }
        },
        data: {
            labels: [
                "First",
                "Second",
                "Third",
                "Fourth"
            ],
            datasets: [
                {
                    data: [300, 50, 100, 60],
                    borderWidth: [0, 0, 0, 0],
                    backgroundColor: [
                        '#6933b9',
                        "#8553d1",
                        "#a372ec",
                        "#be9df1"
                    ],
                    hoverBackgroundColor: [
                        '#6933b9',
                        "#8553d1",
                        "#a372ec",
                        "#be9df1"
                    ]
                }]
        }
    });

    // ------------------------------------------------------- //
    // Pie Chart 2
    // ------------------------------------------------------ //
    var PIECHART = $('#pieChartHome2');
    var myPieChart = new Chart(PIECHART, {
        type: 'doughnut',
        options: {
            cutoutPercentage: 90,
            legend: {
                display: false
            }
        },
        data: {
            labels: [
                "First",
                "Second",
                "Third",
                "Fourth"
            ],
            datasets: [
                {
                    data: [80, 70, 100, 60],
                    borderWidth: [0, 0, 0, 0],
                    backgroundColor: [
                        '#9528b9',
                        "#b046d4",
                        "#c767e7",
                        "#e394fe"
                    ],
                    hoverBackgroundColor: [
                        '#9528b9',
                        "#b046d4",
                        "#c767e7",
                        "#e394fe"
                    ]
                }]
        }
    });

    // ------------------------------------------------------- //
    // Pie Chart 3
    // ------------------------------------------------------ //
    var PIECHART = $('#pieChartHome3');
    var myPieChart = new Chart(PIECHART, {
        type: 'doughnut',
        options: {
            cutoutPercentage: 90,
            legend: {
                display: false
            }
        },
        data: {
            labels: [
                "First",
                "Second",
                "Third",
                "Fourth"
            ],
            datasets: [
                {
                    data: [120, 90, 77, 95],
                    borderWidth: [0, 0, 0, 0],
                    backgroundColor: [
                        '#da4d60',
                        "#e96577",
                        "#f28695",
                        "#ffb6c1"
                    ],
                    hoverBackgroundColor: [
                        '#da4d60',
                        "#e96577",
                        "#f28695",
                        "#ffb6c1"
                    ]
                }]
        }
    });


    // ------------------------------------------------------- //
    // Sales Bar Chart 1
    // ------------------------------------------------------ //
    var BARCHART1 = $('#salesBarChart1');
    var barChartHome = new Chart(BARCHART1, {
        type: 'bar',
        options:
        {
            scales:
            {
                xAxes: [{
                    display: false,
                    barPercentage: 0.2
                }],
                yAxes: [{
                    display: false
                }],
            },
            legend: {
                display: false
            }
        },
        data: {
            labels: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"],
            datasets: [
                {
                    label: "Data Set 1",
                    backgroundColor: [
                        '#EF8C99',
                        '#EF8C99',
                        '#EF8C99',
                        '#EF8C99',
                        '#EF8C99',
                        '#EF8C99',
                        '#EF8C99',
                        '#EF8C99',
                        '#EF8C99',
                        '#EF8C99',
                        '#EF8C99'
                    ],
                    borderColor: [
                        '#EF8C99',
                        '#EF8C99',
                        '#EF8C99',
                        '#EF8C99',
                        '#EF8C99',
                        '#EF8C99',
                        '#EF8C99',
                        '#EF8C99',
                        '#EF8C99',
                        '#EF8C99',
                        '#EF8C99'
                    ],
                    borderWidth: 0.2,
                    data: [35, 55, 65, 85, 40, 30, 18, 35, 20, 70]
                }
            ]
        }
    });

    // ------------------------------------------------------- //
    // Sales Bar Chart 21
    // ------------------------------------------------------ //
    var BARCHART1 = $('#salesBarChart2');
    var barChartHome = new Chart(BARCHART1, {
        type: 'bar',
        options:
        {
            scales:
            {
                xAxes: [{
                    display: false,
                    barPercentage: 0.2
                }],
                yAxes: [{
                    display: false
                }],
            },
            legend: {
                display: false
            }
        },
        data: {
            labels: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"],
            datasets: [
                {
                    label: "Data Set 1",
                    backgroundColor: [
                        '#CF53F9',
                        '#CF53F9',
                        '#CF53F9',
                        '#CF53F9',
                        '#CF53F9',
                        '#CF53F9',
                        '#CF53F9',
                        '#CF53F9',
                        '#CF53F9',
                        '#CF53F9',
                        '#CF53F9'
                    ],
                    borderColor: [
                        '#CF53F9',
                        '#CF53F9',
                        '#CF53F9',
                        '#CF53F9',
                        '#CF53F9',
                        '#CF53F9',
                        '#CF53F9',
                        '#CF53F9',
                        '#CF53F9',
                        '#CF53F9',
                        '#CF53F9'
                    ],
                    borderWidth: 0.2,
                    data: [44, 75, 65, 34, 60, 45, 22, 35, 30, 63]
                }
            ]
        }
    });


    // ------------------------------------------------------- //
    // Pie Chart
    // ------------------------------------------------------ //
    var PIECHARTEXMPLE    = $('#visitPieChart');
    var pieChartExample = new Chart(PIECHARTEXMPLE, {
        type: 'pie',
        options: {
            legend: {
                display: true
            }
        },
        data: {
            labels: [
                "Business",
                "Daily Life",
                "City Traffic",
                "Entertainment",
                "Public Safety"
            ],
            datasets: [
                {
                    data: [BuAct1, DalLif1, CiTra1, EnTai1, PuSaf1],
                    //data:[10,8,5,6,2],
                    borderWidth: 0,
                    backgroundColor: [
                        "#723ac3",
                        "#EE3B3B",
                        "#EAEAEA",
                        "#6B8E23",
                        "#00FFFF"
                    ],
                    hoverBackgroundColor: [
                        "#723ac3",
                        "#EE3B3B",
                        "#EAEAEA",
                        "#6B8E23",
                        "#00FFFF"
                    ]
                }]
            }
    });

    var pieChartExample = {
        responsive: true
    };



    // ------------------------------------------------------- //
    // Pie Chart2
    // ------------------------------------------------------ //
    var PIECHARTEXMPLE2    = $('#visitPieChart2');
    var pieChartExample2 = new Chart(PIECHARTEXMPLE2, {
        type: 'pie',
        options: {
            legend: {
                display: true
            }
        },
        data: {
            labels: [
                "Business",
                "Daily Life",
                "City Traffic",
                "Entertainment",
                "Public Safety"
            ],
            datasets: [
                {
                    data: [BuAct2, DalLif2, CiTra2, EnTai2, PuSaf2],
                    //data:[10,8,5,6,2],
                    borderWidth: 0,
                    backgroundColor: [
                        "#FFB90F",
                        "#6495ED",
                        "#FFFF00",
                        "#7CFC00",
                        "#E0FFFF"
                    ],
                    hoverBackgroundColor: [
                        "#FFB90F",
                        "#6495ED",
                        "#FFFF00",
                        "#7CFC00",
                        "#E0FFFF"
                    ]
                }]
        }
    });

    var pieChartExample2 = {
        responsive: true
    };
});
