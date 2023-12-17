<!DOCTYPE html>
<html lang="en">

<head>
    @include('layout.head')
</head>

<body class="h-screen flex justify-center items-center bg-pastel-background bg-cover bg-no-repeat bg-center shadow-xl">
    <div class="bg-gray-100 mx-auto flex items-center justify-center h-5/6 w-8/12">
        <!-- Left Side: Image -->
        <div class="md:block md:w-1/2 h-full">
            <img src="./assets/img/img-login.png" alt="Background Image" class="object-cover  h-full w-full">
        </div>

        <!-- Right Side: Login Form -->
        <div class="w-1/2 h-full">
            <div class="flex justify-end w-full h-8 my-5 ">
                <img src="./assets/img/logo.svg" alt="" class="me-8 h-full">
            </div>

            <div class="flex flex-col justify-center w-full h-5/6 mt-10">
                @if ($errors->any())
                @foreach ($errors->all() as $error)
                <div class="flex justify-center">
                    <div class="bg-red-500 text-white text-center py-4 px-2 rounded-md -mt-16 mb-2 w-1/2">
                        {{ $error }}
                    </div>
                </div>
                @endforeach

                @endif
                <form class="flex flex-col items-center" action="" method="POST">
                    @csrf
                    <h1 class="mb-4 text-4xl font-bold text-center">Login</h1>
                    <p class="w-full mb-4 mt-4 text-center text-lg text-gray-400">Enter your username and password</p>
                    <div class="mb-4 w-1/2">
                        <input type="text" id="username" name="username" class="w-full px-3 py-2 border rounded-md"
                            placeholder="Username" value="{{ old('username') }}">
                    </div>

                    <div class="mb-4 w-1/2">
                        <input type="password" id="password" name="password" class="w-full px-3 py-2 border rounded-md"
                            placeholder="Password">
                    </div>

                    <div class="mb-5">
                        <input type="checkbox" id="remember" name="remember" class="mr-2 h-5 w-5">
                        <label for="remember" class="text-xl text-gray-400 pb-1">Remember me</label>
                    </div>

                    <button type="submit" name="submit"
                        class="w-full md:w-1/2 bg-purple-custom text-white mx-32 font-bold text-xl py-2 rounded-md">Login</button>
                </form>
            </div>
        </div>
    </div>

</body>

</html>
