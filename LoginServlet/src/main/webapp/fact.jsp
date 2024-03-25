<%!
    int fact(int num) {
        int f = 1;
        for (int i = num; i >= 1 ; i--) {
            f = f * i;
        }
        return f;
    }
%>

<%  int n = Integer.parseInt(request.getParameter("number"));
%>

Factorial: <%= fact(n)%>