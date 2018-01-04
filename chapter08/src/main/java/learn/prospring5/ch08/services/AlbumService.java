package learn.prospring5.ch08.services;


import learn.prospring5.ch08.entities.Album;
import learn.prospring5.ch08.entities.Singer;

import java.util.List;
public interface AlbumService {
    List<Album> findBySinger(Singer singer);
    List<Album> findByTitle(String title);
}