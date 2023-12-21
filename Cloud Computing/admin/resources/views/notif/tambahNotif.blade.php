@include('layout.main')

<!-- Content Area -->
<div class="flex-1 h-screen p-10 pt-20">
    <h1 class="text-pastel-custom text-5xl font-medium">Add Notification</h1>
    <div class="flex justify-between mt-10">
        <a href="{{ route('notification.index') }}"><button
                class="font-bold bg-pastel-custom text-purple-custom rounded-xl p-2.5 px-4 hover:text-black">
                <i class="fas fa-arrow-left mr-2"></i>Back to
                List notification</button></a>
    </div>
    <div class="flex w-full bg-white mt-5 pt-10 px-10 h-fit rounded-3xl">
        <div class="container mx-80 mt-7 mb-7">
            <form action="{{ route('notification.store') }}" method="POST">
                @csrf
                <div class="flex flex-col mt-5">
                    <label for="nameNotif" class="text-xl font-semibold">Name Notification</label>
                    <input type="text" name="title" id="nameNotif"
                        class="@error('title') is-invalid @enderror border border-gray-300 rounded-md p-2.5 focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                        placeholder="Name Notification">
                    <i class="text-sm text-gray-500">*Minimum 3 Character, Maximum 50 Character</i>
                    @error('title')
                    <div class="alert alert-danger mt-1 mb-1">{{ $message }}</div>
                    @enderror
                </div>
                <div class="flex flex-col mt-5">
                    <label for="description" class="text-xl font-semibold">Description Notification</label>
                    <textarea name="description" id="description"
                        class="@error('description') is-invalid @enderror h-36 border border-gray-300 rounded-md p-2.5 focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                        placeholder="Description Notification" maxlength="255"></textarea>
                    <i class="text-sm text-gray-500">*Maximum 255 characters</i>
                    @error('description')
                    <div class="alert alert-danger mt-1 mb-1">{{ $message }}</div>
                    @enderror
                </div>
                <div class="flex flex-col mt-5">
                    <label for="datetime" class="text-xl font-semibold">Date and Time</label>
                    <input type="datetime-local" name="date" id="datetime"
                        class="@error('date') is-invalid @enderror border border-gray-300 rounded-md p-2.5 focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent">
                    <i class="text-sm text-gray-500">*Date and time format</i>
                    @error('date')
                    <div class="alert alert-danger mt-1 mb-1">{{ $message }}</div>
                    @enderror
                </div>
                <div class="flex justify-end mt-5">
                    <button type="submit"
                        class="bg-purple-custom text-pastel-custom p-3 font-semibold rounded-xl hover:bg-pastel-custom hover:text-purple-custom px-7">Add
                        Notification</button>
                </div>
            </form>
        </div>
    </div>
</div>
