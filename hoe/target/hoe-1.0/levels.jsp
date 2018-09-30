<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="onlevel" items="${sessionScope['user'].heroes}">
    - ${onlevels.name}<br>
</c:forEach>


<form method="post" action="levels">
    <div>
        <span>Neve</span>
        <input type="text" name="pname">
    </div>
    <div>
        <span>Leírás</span>
        <textarea name="pdesc"></textarea>
    </div>             
    <div>
        <input type="submit" value="felvitel">
    </div>
</form>        
