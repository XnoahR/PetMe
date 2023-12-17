<?php

namespace App\Http\Controllers;

use App\Models\Pet;
use App\Models\Post;
use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;

class petController extends Controller
{

    function index()
    {
        $post = DB::table('post')
            ->join('user', 'post.id_user', '=', 'user.id')
            ->join('animal', 'post.id_animal', '=', 'animal.id')
            ->select('post.id', 'post.title', 'post.upload_date', 'post.status', 'post.breed', 'post.post_picture', 'user.name', 'animal.type')
            ->orderBy('post.upload_date', 'desc')
            // ->where('post.status', '!=', '2') // 2 = Accepted
            ->paginate(10);

        return view('pet', ['posts' => $post]);
    }

    function showDataById($id)
    {
        $post = DB::table('post')
            ->join('user', 'post.id_user', '=', 'user.id')
            ->join('animal', 'post.id_animal', '=', 'animal.id')
            ->select('post.id', 'post.title', 'post.upload_date', 'post.status', 'post.breed', 'post.post_picture', 'post.description' , 'user.name', 'animal.type')
            ->where('post.id', '=', $id)
            ->first();

        if (!$post) {
            return redirect()->route('pet.index')->with('error', 'Post not found.');
        }

        return view('pet.show', ['post' => $post]);
    }

    function update(Request $request, $id)
    {
        $request -> validate([
            'status' => 'required',
        ]);

        $updateStatus = Pet::find($id);
        $updateStatus->status = $request->input('status');
        $updateStatus->save();

        return redirect()->back()->with('success', 'Status updated successfully.');
    }

    function destroy($id)
    {
        $deletePost = Pet::find($id);
        $deletePost->delete();

        return redirect()->back()->with('success', 'Post deleted successfully.');
    }
}
