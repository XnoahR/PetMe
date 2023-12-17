@include('layout.main')

<!-- Content Area -->
<div class="flex-1 h-screen p-10 pt-20">
    <h1 class="text-pastel-custom text-5xl font-medium">Dashboard</h1>
    <div class="flex w-full bg-white mt-20 p-10 h-auto rounded-3xl">
        <div class="w-1/2 me-2.5">
            <div class="bg-pastel-custom text-purple-custom py-10 px-10 w-full rounded-xl flex items-center h-fit">
                <i class="fas fa-users text-8xl"></i>
                <div class="flex flex-col ms-5">
                    <h1 class="text-3xl font-semibold mb-2">Daftar User</h1>
                    <h1 class="text-7xl font-semibold">{{ $jumlahUser }}</h1>
                </div>
            </div>
        </div>
        <div class="w-1/2 ms-2.5">
            <div class="text-pastel-custom bg-purple-custom py-10 px-10 w-full rounded-xl flex items-center">
                <i class="fa-solid fa-bug text-8xl"></i>
                <div class="flex flex-col ms-5">
                    <h1 class="text-3xl font-semibold mb-2">Aproval Pet</h1>
                <h1 class="text-7xl font-semibold">{{ $jumlahPet }}</h1>
                </div>
            </div>
        </div>
    </div>
</div>
