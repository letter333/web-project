<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<script src="https://d3js.org/d3.v4.min.js"></script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width">
<title>OHAll : d3</title>
<link href="${path }/resources/style/d3.css" rel="stylesheet" type="text/css" />
</head>
<body>
<c:import url="../component/header.jsp" />
    <div class="cafe-graph"></div>

<script>
// 값 분류
var data = []
var sum = 0
var data_sum = []

<c:forEach var="e" items="${segu}">
if (data.length == 0){
	data.push({
		name: "${e.address_old}".split(' ')[2],
		value : 1
	})
}
else {
	for (i = 0; i < data.length; i++) {
		if (data[i].name == "${e.address_old}".split(' ')[2]) {
			data[i].value += 1
			break
		}
		if (i == data.length - 1) {
			data.push({
				name: "${e.address_old}".split(' ')[2],
				value : 0
			})
		}
	}
}
sum+=1
</c:forEach>



// 내림차순 정렬, 백분률
var data_s = data.sort((a,b) => (b.value - a.value))
for (i = 0; i < data_s.length; i++) {
	console.log(data_s[i].name+", "+data_s[i].value)
}
console.log("sum = " + sum)

data_sum[4] = 0
data_s[4].name = "기타"
for (i = 0; i < data_s.length; i++) {
	if (i < 4)
		data_sum[i] = Math.round(data_s[i].value / sum * 100)
	else
		data_sum[4] += data_s[i].value
	if (i == data_s.length - 1)
		data_sum[4] = Math.round(data_sum[4] / sum * 100)
}
for (i = 0; i < 5; i++) {
	console.log(data_sum[i])
}



// 그래프
var w = 850, h = 700;
var graphData = [data_sum[0], data_sum[1], data_sum[2], data_sum[3], data_sum[4]];
var colorData = ["rgb(255, 153, 204)", "rgb(178, 178, 178)", "rgb(153, 255, 255)", "rgb(130, 130, 130)", "rgb(227, 227, 227)"];
// var colorData = ["rgb(227, 227, 227)", "rgb(178, 178, 178)", "rgb(227, 227, 227)", "rgb(178, 178, 178)", "rgb(150, 150, 150)"];
var pie = d3.pie();
var arc = d3.arc().innerRadius(60).outerRadius(250);

var svg = d3.select(".cafe-graph")
    .append("svg")
    .attr("width", w)
    .attr("height", h)
    .attr("id", "graphWrap");

var g = svg.selectAll(".pie")
    .data(pie(graphData))
    .enter()
    .append("g")
    .attr("class", "pie")
    .attr("transform","translate("+w/2+","+h/2+")");

// path 태그로 차트에 색을 넣기
g.append("path")
    // .attr("d", arc) // 미리 색을 칠해놓음
    .style("fill", function(d, i) {
        return colorData[i];
    }) // 애니메이션이 싫을경우 arc 를 활성화시키고 아래내용을 주석
    .transition()
    .duration(400)
    .delay(function(d, i) { // 그릴 원 그래프의 시간을 어긋나게 표시
        return i * 400;
    })
    .attrTween("d", function(d, i) { // 보간 처리
        var interpolate = d3.interpolate(
            {startAngle : d.startAngle, endAngle : d.startAngle}, // 각 부분의 시작 각도
            {startAngle : d.startAngle, endAngle : d.endAngle} // 각 부분의 종료 각도
        );
        return function(t){
            return arc(interpolate(t)); // 시간에 따라 처리
        }
    });

// text 태그로 배열 값 넣기
g.append("text")
    .attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")"; })
    .attr("dy", ".35em")
    .style("text-anchor", "middle")
    .text(function(d, i) {
        return data_s[i].name + " " + graphData[i] + "%";
    });

// text 정 중앙에 텍스트 넣기
svg.append("text")
    .attr("class", "total")
    .attr("transform", "translate("+(w/2-15)+", "+(h/2-5)+")")
    .text("서구");

svg.append("text")
	.attr("class", "total")
	.attr("transform", "translate("+(w/2-34)+", "+(h/2+20)+")")
	.text("카페 비율");
//.text("합계:" + d3.sum(graphData)+"%");
</script>
    
    
    
    
    
    
    
    
    
    
<script>
// 값 분류
var data = []
var sum = 0
var data_sum = []

<c:forEach var="e" items="${susunggu}">
if (data.length == 0){
	data.push({
		name: "${e.address_old}".split(' ')[2],
		value : 1
	})
}
else {
	for (i = 0; i < data.length; i++) {
		if (data[i].name == "${e.address_old}".split(' ')[2]) {
			data[i].value += 1
			break
		}
		if (i == data.length - 1) {
			data.push({
				name: "${e.address_old}".split(' ')[2],
				value : 0
			})
		}
	}
}
sum+=1
</c:forEach>



// 내림차순 정렬, 백분률
var data_s = data.sort((a,b) => (b.value - a.value))
for (i = 0; i < data_s.length; i++) {
	console.log(data_s[i].name+", "+data_s[i].value)
}
console.log("sum = " + sum)

data_sum[4] = 0
data_s[3].name = "기타"
for (i = 0; i < data_s.length; i++) {
	if (i < 4)
		data_sum[i] = Math.round(data_s[i].value / sum * 100)
	else
		data_sum[4] += data_s[i].value
	if (i == data_s.length - 1)
		data_sum[4] = Math.round(data_sum[4] / sum * 100)
}
for (i = 0; i < 5; i++) {
	console.log(data_sum[i])
}



// 그래프
var w = 850, h = 700;
var graphData = [data_sum[0], data_sum[1], data_sum[2], data_sum[3], data_sum[4]];
var colorData = ["rgb(255, 153, 204)", "rgb(178, 178, 178)", "rgb(153, 255, 255)", "rgb(130, 130, 130)", "rgb(227, 227, 227)"];
// var colorData = ["rgb(227, 227, 227)", "rgb(178, 178, 178)", "rgb(227, 227, 227)", "rgb(178, 178, 178)", "rgb(150, 150, 150)"];
var pie = d3.pie();
var arc = d3.arc().innerRadius(60).outerRadius(250);

var svg = d3.select(".cafe-graph")
    .append("svg")
    .attr("width", w)
    .attr("height", h)
    .attr("id", "graphWrap");

var g = svg.selectAll(".pie")
    .data(pie(graphData))
    .enter()
    .append("g")
    .attr("class", "pie")
    .attr("transform","translate("+w/2+","+h/2+")");

// path 태그로 차트에 색을 넣기
g.append("path")
    // .attr("d", arc) // 미리 색을 칠해놓음
    .style("fill", function(d, i) {
        return colorData[i];
    }) // 애니메이션이 싫을경우 arc 를 활성화시키고 아래내용을 주석
    .transition()
    .duration(400)
    .delay(function(d, i) { // 그릴 원 그래프의 시간을 어긋나게 표시
        return i * 400;
    })
    .attrTween("d", function(d, i) { // 보간 처리
        var interpolate = d3.interpolate(
            {startAngle : d.startAngle, endAngle : d.startAngle}, // 각 부분의 시작 각도
            {startAngle : d.startAngle, endAngle : d.endAngle} // 각 부분의 종료 각도
        );
        return function(t){
            return arc(interpolate(t)); // 시간에 따라 처리
        }
    });

// text 태그로 배열 값 넣기
g.append("text")
    .attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")"; })
    .attr("dy", ".35em")
    .style("text-anchor", "middle")
    .text(function(d, i) {
        return data_s[i].name + " " + graphData[i] + "%";
    });

// text 정 중앙에 텍스트 넣기
svg.append("text")
    .attr("class", "total")
    .attr("transform", "translate("+(w/2-23)+", "+(h/2-5)+")")
    .text("수성구");

svg.append("text")
	.attr("class", "total")
	.attr("transform", "translate("+(w/2-34)+", "+(h/2+20)+")")
	.text("카페 비율");
//.text("합계:" + d3.sum(graphData)+"%");
</script>



<h1>&nbsp지역별 카페 목록</h1><br>
<th>&nbsp서구<th>
<table>
<thead>
<tr>
<td>연번&nbsp</td>
<td>업종명&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
<td>업소명</td>
<td>도로명 주소</td>
<td>지번 주소</td>
<td>면적&nbsp&nbsp&nbsp&nbsp</td>
<td>전화번호</td>
<td>업태명</td>
</tr>
<c:forEach var="e" items="${segu}"> 
<tr>  
<td>${e.cafenum}</td>
<td>${e.category}</td>
<td>${e.name}</td>
<td>${e.address_new}</td>
<td>${e.address_old}</td>
<td>${e.area}</td>
<td>${e.callnum}</td>
<td>${e.bname}</td>
</tr>
</c:forEach>
</tbody>
</table>
<br><br><br>

<th>&nbsp수성구<th>
<table>
<thead>
<tr>
<td>연번&nbsp</td>
<td>업종명&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
<td>업소명</td>
<td>도로명 주소</td>
<td>지번 주소</td>
<td>면적&nbsp&nbsp&nbsp&nbsp</td>
<td>전화번호</td>
<td>업태명</td>
</tr>
<c:forEach var="u" items="${susunggu}"> 
<tr>  
<td>${u.cafenum}</td>
<td>${u.category}</td>
<td>${u.name}</td>
<td>${u.address_new}</td>
<td>${u.address_old}</td>
<td>${u.area}</td>
<td>${u.callnum}</td>
<td>${u.bname}</td>
</tr>
</c:forEach>
</tbody>
</table>



<%-- <script src="https://d3js.org/d3.v4.min.js"></script>
<script src="${path }/resources/js/d3.js"></script> --%>
</body>
</html>