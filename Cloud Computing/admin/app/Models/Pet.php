<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Pet extends Model
{
    use HasFactory;
    protected $table = 'post';
    protected $fillable = [
        'id',
        'title',
        'upload_date',
        'status',
        'breed',
    ];
    public $timestamps = false;
}
