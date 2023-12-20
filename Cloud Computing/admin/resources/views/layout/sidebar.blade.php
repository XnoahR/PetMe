<body class="bg-gray-100 -z-20">
    <div class="w-full h-12 bg-purple-custom absolute -z-10 py-40"></div>
    <div class="flex z-50">
        <div class="w-1/5 sm:h-50r xl:h-screen bg-white p-8 pt-14 pb-14 ml-20 mt-20 rounded-3xl text-xl font-bold text-center shadow-xl">
            <!-- Logo -->
            <div class="mb-8 flex items-center justify-center">
                <img src="{{ asset('assets/img/logo.svg') }}" alt="Logo" class="w-3/4">
            </div>
            <!-- Menu -->
            <nav>
                <ul>
                    <li class="mb-4">
                        <a href="{{ route('dashboard') }}" class="p-2.5 text-purple-custom hover:bg-pastel-custom hover:rounded-xl"><i
                                class="fa-solid fa-house-chimney-window"></i> Dashboard</a>
                    </li>
                    <li class="mb-4">
                        <a href="{{ route('user.index') }}" class="p-2.5 text-purple-custom hover:bg-pastel-custom hover:rounded-xl"><i
                                class="fas fa-users"></i> Daftar User</a>
                    </li>
                    <li class="mb-4">
                        <a href="{{ route('pet.index') }}" class="p-2.5 text-purple-custom hover:bg-pastel-custom hover:rounded-xl">
                            <i class="fa-solid fa-paw"></i> Approval Pet</a>
                    </li>
                    <li class="mb-4">
                        <a href="{{ route('notification.index') }}" class="p-2.5 text-purple-custom hover:bg-pastel-custom hover:rounded-xl"><i
                                class="fa-solid fa-bell"></i> Notification</a>
                    </li>
                </ul>
            </nav>
            <!-- Logout -->
            <div class="mt-72">
                <a href="/logout" class="px-2.5 py-2 text-red-700 hover:border-2 hover:border-red-700 hover:bg-red-200 hover:rounded-xl"><i
                        class="fa fa-sign-out-alt"></i> Logout</a>
            </div>
        </div>
