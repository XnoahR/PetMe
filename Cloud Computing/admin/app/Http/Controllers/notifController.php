<?php

namespace App\Http\Controllers;

use App\Models\Notif;
use Illuminate\Http\Request;

class notifController extends Controller
{
    public function index()
    {
        $notification = Notif::orderBy('id', 'desc')->paginate(10);
        return view('notification', compact('notification'));
    }

    public function create()
    {
        return view('notif.tambahNotif');
    }

    public function store(Request $request)
    {
        $this->validate($request, [
            'title' => 'required|min:3|max:50',
            'description' => 'required|min:3|max:255',
            'date' => 'required|date',
        ]);

        Notif::create([
            'title' => $request->title,
            'description' => $request->description,
            'date' => $request->date,
        ]);

        return redirect()->route('notification.index')->with('success', 'Notifikasi berhasil ditambahkan')->withInput();
    }

    public function edit($id)
    {
        $notif = Notif::findOrFail($id);
        return view('notif.editNotif', compact('notif'));
    }

    public function update(Request $request, $id)
    {
        $notif = Notif::findOrFail($id);

        $this->validate($request, [
            'title' => 'required|min:3|max:50',
            'description' => 'required|min:3|max:255',
            'date' => 'required|date',
        ]);

        $notif->update([
            'title' => $request->title,
            'description' => $request->description,
            'date' => $request->date,
        ]);

        return redirect()->route('notification.index')->with('success', 'Notifikasi berhasil diubah')->withInput();
    }

    public function destroy($id)
    {
        $notif = Notif::findOrFail($id);
        $notif->delete();
        return redirect()->route('notification.index')->with('success', 'Notifikasi berhasil dihapus');
    }
}
