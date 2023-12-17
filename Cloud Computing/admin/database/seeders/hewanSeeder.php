<?php

namespace Database\Seeders;

use Illuminate\Support\Facades\DB;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class hewanSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        $hewan = [
            [
                'type' => 'cat',
                'breed' => 'Anggora',
                'fur' => 'long',
            ],
            [
                'type' => 'cat',
                'breed' => 'Persia',
                'fur' => 'long',
            ],
            [
                'type' => 'cat',
                'breed' => 'Siam',
                'fur' => 'short',
            ],
            [
                'type' => 'cat',
                'breed' => 'Bengal',
                'fur' => 'short',
            ],
            [
                'type' => 'cat',
                'breed' => 'Kucing Kampung',
                'fur' => 'short',
            ],
            [
                'type' => 'dog',
                'breed' => 'Bulldog',
                'fur' => 'short',
            ],
            [
                'type' => 'dog',
                'breed' => 'Pug',
                'fur' => 'short',
            ],
            [
                'type' => 'dog',
                'breed' => 'Poodle',
                'fur' => 'long',
            ],
            [
                'type' => 'dog',
                'breed' => 'Chihuahua',
                'fur' => 'short',
            ],
            [
                'type' => 'dog',
                'breed' => 'Golden Retriever',
                'fur' => 'long',
            ],
            [
                'type' => 'dog',
                'breed' => 'Labrador Retriever',
                'fur' => 'long',
            ],
            [
                'type' => 'dog',
                'breed' => 'Siberian Husky',
                'fur' => 'long',
            ],
            [
                'type' => 'dog',
                'breed' => 'Beagle',
                'fur' => 'short',
            ],
            [
                'type' => 'dog',
                'breed' => 'Rottweiler',
                'fur' => 'short',
            ],
            [
                'type' => 'dog',
                'breed' => 'German Shepherd',
                'fur' => 'long',
            ],
            [
                'type' => 'dog',
                'breed' => 'Doberman',
                'fur' => 'short',
            ],
            [
                'type' => 'dog',
                'breed' => 'Dachshund',
                'fur' => 'short',
            ],
        ];

        foreach ($hewan as $h) {
            DB::table('animal')->insert([
                'type' => $h['type'],
                'breed' => $h['breed'],
                'fur' => $h['fur'],
            ]);
        }

    }

}
