<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Notif extends Model
{
    use HasFactory;
    protected $table = 'notification';
    protected $fillable = [
        'id',
        'title',
        'description',
        'date',
    ];
    public $timestamps = false;
}
