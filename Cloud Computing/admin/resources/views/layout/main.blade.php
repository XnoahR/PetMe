<!DOCTYPE html>
<html lang="en">
<head>
    @include('layout.head')
</head>
<body @if(Session::get('zoomed_out')) onload="zoom()" @endif>
    @include('layout.sidebar')
        @yield('content')
</body>
</html>
<script>
    function zoom() {
        document.body.style.zoom = "80%"
    }
</script>
<script src="https://kit.fontawesome.com/984a8c9bbd.js" crossorigin="anonymous"></script>
