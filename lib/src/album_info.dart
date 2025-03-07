part of flutter_audio_query;

/// AlbumInfo class holds all information about a specific artist album.
class AlbumInfo extends DataModel {
  AlbumInfo._(Map<dynamic, dynamic> map) : super._(map);

  Map<String, dynamic> toJson(){
    return {
      'artist' : _data['artist'],
      'title' : _data['title'],
      'albumArt' : _data['albumArt'],
      'firstYear' : _data['firstYear'],
      'lastYear' : _data['lastYear'],
      'numberOfSongs' : _data['numberOfSongs'],
      '_id' : super.id

    };
  }

  static fromJson(Map<String, dynamic> parsedJson){
    return AlbumInfo._(parsedJson);
  }

  /// Returns the album title .
  String get title => _data['album'];

  /// Returns an image file path fof this album artwork
  /// or null if there is no one.
  String get albumArt => _data['album_art'];

  ///Returns the artist name that songs appears in this album.
  String get artist => _data['artist'];

  String get firstYear => _data['minyear'];

  String get lastYear => _data['maxyear'];

  /// Returns the number of songs that this album contains.
  String get numberOfSongs => _data['numsongs'];
}
