@include('layout.main')

<!-- Content Area -->
<div class="flex-1 h-screen p-10 pt-20">
    <h1 class="text-pastel-custom text-5xl font-medium">Edit User</h1>
    <div class="flex justify-between mt-10">
        <a href="{{ route('user.index') }}"><button class="font-bold bg-pastel-custom text-purple-custom rounded-xl p-2.5 px-4 hover:text-black">Back to Daftar User</button></a>
    </div>
    <div class="flex w-full bg-white mt-5 pt-10 px-10 h-fit rounded-3xl">
        <div class="container mx-80 mt-7 mb-7">
            <form action="{{ route('user.update', $user->id) }}" method="POST">
                @csrf
                @method('PUT')
                <div class="flex flex-col mt-5">
                    <label for="name" class="text-xl font-semibold">Nama</label>
                    <input type="text" name="name" id="name"
                        class="@error('name') is-invalid @enderror border border-gray-300 rounded-md p-2.5 focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                        placeholder="Nama Lengkap" value="{{ old('name', $user->name) }}">
                    <i class="text-sm text-gray-500">*Minimum 3 Character, Maximum 50 Character</i>
                    @error('name')
                    <div class="alert alert-danger mt-1 mb-1">{{ $message }}</div>
                    @enderror
                </div>
                <div class="flex flex-col mt-5">
                    <label for="username" class="text-xl font-semibold">Username</label>
                    <input type="text" name="username" id="username"
                        class="@error('username') is-invalid @enderror border border-gray-300 rounded-md p-2.5 focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                        placeholder="Username" value="{{ old('username', $user->username) }}">
                    <i class="text-sm text-gray-500">*Minimum 3 Character, Maximum 10 Character</i>
                    @error('username')
                    <div class="alert alert-danger mt-1 mb-1">{{ $message }}</div>
                    @enderror
                </div>
                <div class="flex flex-col mt-5">
                    <label for="email" class="text-xl font-semibold">Email</label>
                    <input type="email" name="email" id="email"
                        class="@error('email') is-invalid @enderror border border-gray-300 rounded-md p-2.5 focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                        placeholder="Email" value="{{ old('email', $user->email) }}">
                    <i class="text-sm text-gray-500">*Email format only</i>
                    @error('email')
                    <div class="alert alert-danger mt-1 mb-1">{{ $message }}</div>
                    @enderror
                </div>
                <div class="flex flex-col mt-5">
                    <label for="phone" class="text-xl font-semibold">Phone</label>
                    <input type="text" name="phone" id="phone"
                        class="@error('phone') is-invalid @enderror border border-gray-300 rounded-md p-2.5 focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                        placeholder="Phone" value="{{ old('phone', $user->phone) }}">
                    <i class="text-sm text-gray-500">*Phone format only (0812-3456-7890) 13-digit</i>
                    @error('phone')
                    <div class="alert alert-danger mt-1 mb-1">{{ $message }}</div>
                    @enderror
                </div>
                <div class="flex flex-col mt-5">
                    <label for="password" class="text-xl font-semibold">Password</label>
                    <input type="password" name="password" id="password"
                        class="@error('password') is-invalid @enderror border border-gray-300 rounded-md p-2.5 focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                        placeholder="Password" value="{{ old('password', $user->password) }}">
                    <i class="text-sm text-gray-500">*Masukkan Kata Sandi Baru <br> Minimum 3 Character, Maximum 15 Character</i>
                </div>
                <div class="flex justify-end mt-5">
                    <button type="submit"
                        class="bg-purple-custom text-pastel-custom p-3 font-semibold rounded-xl hover:bg-pastel-custom hover:text-purple-custom px-7">Update User</button>
                </div>
            </form>
        </div>
    </div>
</div>
