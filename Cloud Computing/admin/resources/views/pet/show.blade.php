@include('layout.main')

<!-- Content Area -->
<div class="flex-1 h-screen p-10 pt-20">
    <h1 class="text-pastel-custom text-5xl font-medium">Show Data Approval Pet</h1>
    <div class="flex justify-between mt-10">
        <a href="{{ route("pet.index") }}"><button
                class="font-bold bg-pastel-custom text-purple-custom rounded-xl p-2.5 px-4 hover:text-black">Back to
                Approval Pet</button></a>
    </div>
    <div class="flex w-full bg-white mt-5 py-10 px-10 h-fit rounded-3xl">
        <div class="container my-7">
            <form class="flex justify-center items-center">
                <div class="w-1/2">
                    <div class="flex items-center justify-center">
                        <img src="{{ $post->post_picture }}" alt="Post Picture"
                            class="max-w-full max-h-96 object-contain">
                    </div>
                </div>
                <div class="mt-5 ml-auto w-1/2">
                    <div class="flex flex-col w-3/4">
                        <label for="name" class="text-xl font-semibold">Nama</label>
                        <input type="text" name="name" id="name"
                            class="border border-gray-300 rounded-md p-2.5 focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                            placeholder="Nama Lengkap" value="{{ old('name', $post->title) }}" disabled>
                    </div>
                    <div class="flex flex-col w-3/4 mt-1.5">
                        <label for="type" class="text-lg font-semibold">Type</label>
                        <input type="text" name="type" id="type"
                            class="border border-gray-300 rounded-md p-2.5 focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                            placeholder="Type" value="{{ old('type', $post->type) }}" disabled>
                    </div>
                    <div class="flex flex-col w-3/4 mt-1.5">
                        <label for="breed" class="text-lg font-semibold">Breed</label>
                        <input type="text" name="breed" id="breed"
                            class="border border-gray-300 rounded-md p-2.5 focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                            placeholder="Breed" value="{{ old('breed', $post->breed) }}" disabled>
                        <i class="text-sm text-gray-500">*Breed of the pet</i>
                    </div>
                    <div class="flex flex-col w-3/4 mt-1.5">
                        <label for="breed" class="text-lg font-semibold">Description</label>
                        <input type="text" name="breed" id="breed"
                            class="border border-gray-300 rounded-md p-2.5 focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                            placeholder="Breed" value="{{ old('breed', $post->description) }}" disabled>
                    </div>
                    <div class="flex flex-col w-3/4 mt-10 text-center">
                        <label for="status" class="text-xl font-semibold">Status</label>
                        @if($post->status == 1)
                        <span class="text-red-500 font-bold text-2xl">Not approved</span>
                        @elseif($post->status == 2)
                        <span class="text-green-500">Approved</span>
                        @endif
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
