@include('layout.main')

<!-- Content Area -->
<div class="flex-1 h-screen p-10 pt-20">
    <div class="flex justify-between">
    <h1 class="text-pastel-custom text-5xl font-medium w-1/2">Daftar User</h1>
    @if (session('success'))
    <div id="successMessage" class="bg-green-200 p-3 rounded-md shadow-sm mb-5 w-1/4">
        <div class="flex">
            <div class="flex-shrink-0">
                <svg class="h-5 w-5 text-green-500" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd"
                        d="M2.293 8.293a1 1 0 010-1.414l6-6a1 1 0 011.414
                        0l6 6a1 1 0 01-1.414 1.414L11 4.414V16a1 1
                        0 01-2 0V4.414L3.707 8.707a1 1 0 01-1.414
                        0z" clip-rule="evenodd" />
                </svg>
            </div>
            <div class="ml-3">
                <p class="text-sm leading-5 font-medium text-green-500">
                    {{ session('success') }}
                </p>
            </div>
        </div>
    </div>
    @endif
    </div>
    <div class="flex justify-between mt-10">
        <a href="{{ route('user.create') }}"
            class="font-bold bg-pastel-custom text-purple-custom rounded-xl p-2.5 px-4 hover:text-black">Tambah
            User</a>
        <input type="text" id="liveSearchInput"
            class="w-1/4 border border-gray-300 rounded-md p-2.5 focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
            placeholder="Search...">
    </div>
    <div class="flex w-full bg-white mt-5 px-10 h-auto rounded-3xl">
        <div class="container mx-auto py-10">
            <table class="table-auto border-collapse w-full mb-3">
                <thead>
                    <tr class="capitalize text-base leading-normal border-b-4 text-center">
                        <th class="py-3 px-1 text-left">#</th>
                        <th class="py-3 px-1 text-left">Nama</th>
                        <th class="py-3 px-6 text-left">Username</th>
                        <th class="py-3 px-6">Email</th>
                        <th class="py-3 px-6">Role</th>
                        <th class="py-3 px-6">Aksi</th>
                    </tr>
                </thead>
                <tbody class="text-gray-600 text-base font-medium">
                    @forelse ($users as $index => $user)
                    <tr class="border-b-4 text-center border-gray-200 hover:bg-gray-100">
                        <td class="py-1.5 px-1 whitespace-nowrap text-left">
                            <span class="font-normal">{{ $index + $users->firstItem() }}</span>
                        </td>
                        <td class="py-1.5 px-1 whitespace-nowrap text-left">
                            <span class="font-medium">{{ $user->name }}</span>
                        </td>
                        <td class="py-1.5 px-6 text-left">
                            <span>{{ $user->username }}</span>
                        </td>
                        <td class="py-1.5 px-6">
                            <span>{{ $user->email }}</span>
                        </td>
                        <td class="py-1.5 px-6">
                            <span class="bg-green-200 text-green-600 py-1 px-3 rounded-full text-base">
                                @if ($user->role == 1)
                                User
                                @else
                                {{ $user->role }}
                                @endif
                            </span>
                        </td>
                        <td class="py-1.5 px-6">
                            <div class="flex item-center justify-center">
                                <a href="{{ route('user.edit', $user->id) }}" class="border border-blue-500 text-blue-500 px-3.5 py-0.5 rounded-md hover:bg-blue-500 hover:text-white"><i
                                class="fas fa-edit"></i> Edit</a>
                                <form id="deleteForm" method="POST" action="{{ route('user.destroy', $user->id) }}">
                                    @csrf
                                    @method('DELETE')
                                    <button type="button" onclick="showModal({{ $user->id }})" class="border border-red-500 text-red-500 px-3.5 py-0.5 rounded-md ml-1 hover:bg-red-500 hover:text-white">
                                        <i class="fas fa-trash"></i> Delete
                                    </button>
                                </form>
                            </div>
                        </td>
                    </tr>
                    @empty
                    <tr>
                        <td colspan="5" class="py-3 px-6 text-center border-4 border-red-500 text-red-500">
                            <span class="font-bold text-xl">Tidak ada data</span>
                        </td>
                    </tr>
                    @endforelse
                </tbody>
            </table>
            {{ $users->links() }}
        </div>
    </div>
</div>

<div id="popup-modal" tabindex="-1" class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 flex justify-center items-center w-full md:inset-0 h-full max-h-full bg-opacity-50 bg-gray-500 backdrop-filter backdrop-blur-sm">
    <div class="relative p-4 w-full max-w-md max-h-full">
        <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
            <button type="button" onclick="hideModal()" class="absolute top-3 end-2.5 text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white" data-modal-hide="popup-modal">
                <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
                </svg>
                <span class="sr-only">Close modal</span>
            </button>
            <div class="p-4 md:p-5 text-center">
                <svg class="mx-auto mb-4 text-gray-400 w-12 h-12 dark:text-gray-200" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 11V6m0 8h.01M19 10a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"/>
                </svg>
                <h3 class="mb-5 text-lg font-normal text-gray-500 dark:text-gray-400">Are you sure you want to delete ?</b></h3>
                <button data-modal-hide="popup-modal" type="button" onclick="document.getElementById('deleteForm').submit();" class="text-white bg-red-600 hover:bg-red-800 focus:ring-4 focus:outline-none focus:ring-red-300 dark:focus:ring-red-800 font-medium rounded-lg text-sm inline-flex items-center px-5 py-2.5 text-center me-2">
                    Yes, I'm sure
                </button>
                <button data-modal-hide="popup-modal" type="button" onclick="hideModal()" class="text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-gray-200 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600">No, cancel</button>
            </div>
        </div>
    </div>
</div>

<script>
    // Modal
    function showModal(id) {
        const modal = document.getElementById('popup-modal');
        modal.classList.remove('hidden');

        const deleteForm = document.getElementById('deleteForm');
        deleteForm.action = "{{ route('user.destroy', '') }}" + '/' + id;
    }

    function hideModal() {
        const modal = document.getElementById('popup-modal');
        modal.classList.add('hidden');
    }

    // Live Search
    const liveSearchInput = document.getElementById("liveSearchInput");
    const tables = document.querySelectorAll("table");

    liveSearchInput.addEventListener("keyup", function (e) {
    const searchString = e.target.value.toLowerCase();

    tables.forEach((table) => {
        const tableRows = table.querySelectorAll("tbody tr");

        tableRows.forEach((row) => {
            const nama = row
                .querySelector("td:nth-child(1)")
                .textContent.toLowerCase();
            const username = row
                .querySelector("td:nth-child(2)")
                .textContent.toLowerCase();
            const email = row
                .querySelector("td:nth-child(3)")
                .textContent.toLowerCase();

            const tds = row.querySelectorAll("td");
            if (
                nama.includes(searchString) ||
                username.includes(searchString) ||
                email.includes(searchString)
            ) {
                tds.forEach((td) => {
                    td.style.display = "";
                });
            } else {
                tds.forEach((td) => {
                    td.style.display = "none";
                });
            }
        });
    });
});

    // Notification Success
    setTimeout(function() {
        var successMessage = document.getElementById('successMessage');
        successMessage.style.opacity = '0';
        successMessage.style.transition = 'opacity 0.5s ease-in-out';

        setTimeout(function() {
            successMessage.style.display = 'none';
        }, 500);
    }, 5000);
</script>
