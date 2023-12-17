<!DOCTYPE html>
<html lang="en">
<head>
    @include('layout.head')
    <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" rel="stylesheet"/>
</head>
<body>
    @include('layout.sidebar')
        @yield('content')
</body>
</html>
<script src="https://kit.fontawesome.com/984a8c9bbd.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
