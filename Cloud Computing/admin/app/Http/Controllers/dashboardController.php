<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Pet;
use App\Models\User;

class dashboardController extends Controller
{
    public function hitungJumlahData()
    {
        $jumlahUser = User::where('role', 1)->count();
        $jumlahPet = Pet::count();

        return view('dashboard', compact('jumlahUser', 'jumlahPet'));
    }
}
