<?php

use App\Http\Controllers\authController;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\dashboardController;
use App\Http\Controllers\userController;
use App\Http\Controllers\petController;
use App\Http\Controllers\notifController;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/
Route::middleware(['guest'])->group(function () {
    Route::get('/login', [authController::class, 'index'])->name('login');
    Route::post('/login', [authController::class, 'login']);
});

Route::middleware(['auth'])->group(function () {
    Route::get('/', [dashboardController::class, 'hitungJumlahData'])->name('dashboard');
    Route::get('/logout', [authController::class, 'logout']);
    Route::resource('/user', userController::class);
    Route::get('/pet', [petController::class, 'index'])->name('pet.index');
    Route::get('/pet{id}', [petController::class, 'showDataById'])->name('pet.show');
    Route::patch('/pet{id}', [petController::class, 'update'])->name('pet.update');
    Route::delete('/pet{id}', [petController::class, 'destroy'])->name('pet.destroy');
    Route::resource('/notification', notifController::class);
});

