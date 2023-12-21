<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('post', function (Blueprint $table) {
            $table->increments('id');
            $table->string('title');
            $table->date('upload_date');
            $table->integer('status')->default(1);
            $table->string('breed');
            $table->string('post_picture')->nullable();
            $table->text('description');
            $table->float('latitude');
            $table->float('longitude');
            $table->integer('id_user')->unsigned();
            $table->integer('id_animal')->unsigned();
            $table->foreign('id_user')->references('id')->on('user');
            $table->foreign('id_animal')->references('id')->on('animal');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('post');
    }
};
