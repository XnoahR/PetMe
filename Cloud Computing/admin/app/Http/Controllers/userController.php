<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Http\RedirectResponse;

class userController extends Controller
{
    public function index()
    {
        $users = User::where('role', 1)->latest()->paginate(10);

        return view('user', compact('users'));
    }

    public function create()
    {
        return view('user.tambahUser');
    }

    public function store(Request $request): RedirectResponse
    {
        $this->validate($request, [
            'name' => 'required|min:3|max:50',
            'username' => 'required|min:3|max:10|unique:user,username',
            'email' => 'required|email|unique:user,email',
            'phone' => 'nullable|numeric|digits_between:10,13',
            'password' => 'required|min:3|max:15',
        ]);

        User::create( [
            'name' => $request->name,
            'username' => $request->username,
            'email' => $request->email,
            'phone' => $request->phone ?? null,
            'password' => $request->password,
            'profile_picture' => 'https://storage.googleapis.com/petmebucket/user_data/paws.png', // Added column with default value
        ]);

        return redirect()->route('user.index')->with('success', 'User berhasil ditambahkan')->withInput();
    }

    public function edit($id)
    {
        $user = User::findOrFail($id);

        return view('user.editUser', compact('user'));
    }

    public function update(Request $request, $id): RedirectResponse
    {
        $user = User::findOrFail($id);

        $this->validate($request, [
            'name' => 'required|min:3|max:50',
            'username' => 'required|min:3|max:10|unique:user,username,' . $user->id,
            'email' => 'required|email|unique:user,email,' . $user->id,
            'phone' => 'nullable|numeric|digits_between:10,13',
            'password' => 'required|min:3|max:15',
        ]);

        $user->update([
            'name' => $request->name,
            'username' => $request->username,
            'email' => $request->email,
            'phone' => $request->phone ?? null,
            'password' => $request->password,
        ]);

        return redirect()->route('user.index')->with('success', 'User berhasil diupdate');
    }

    public function destroy($id): RedirectResponse
    {
        $user = User::findOrFail($id);
        $user->delete();

        return redirect()->route('user.index')->with('success', 'User berhasil dihapus');
    }
}
