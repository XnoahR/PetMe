<?php

namespace Database\Seeders;

use App\Models\User;
use Illuminate\Support\Facades\Hash;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class userSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        $userData = [
            [
                'username' => 'admin',
                'email' => 'admin@gmail.com',
                'password' => Hash::make('admin'),
                'name' => 'admin',
                'phone' => '081234567890',
                'role' => '2',
            ],
            [
                'username' => 'user',
                'email' => 'user@gmail.com',
                'password' => Hash::make('user'),
                'name' => 'user',
                'phone' => '081234567890',
                'role' => '1',
            ],
        ];

        foreach ($userData as $key => $val) {
            User::create($val);
        }
    }
}
