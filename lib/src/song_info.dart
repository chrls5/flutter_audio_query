part of flutter_audio_query;

/// SongInfo class holds all information about a specific song audio file.
class SongInfo extends DataModel {
  SongInfo._(Map<dynamic, dynamic> map) : super._(map);

  Map<String, dynamic> toJson(){
    return {
      'album_id' : _data['album_id'],
      'artist_id' : _data['artist_id'],
      'artist' : _data['artist'],
      'album' : _data['album'],
      'genre_name' : _data['genre_name'],
      'title' : _data['title'],
      '_display_name' : _data['_display_name'],
      'composer' : _data['composer'],
      'year' : _data['year'],
      'track' : _data['track'],
      'duration' : _data['duration'],
      'bookmark' : _data['bookmark'],
      '_data' : _data['_data'],
      'uri' : _data['uri'],
      '_size' : _data['_size'],
      'album_artwork' : _data['album_artwork'],
      'is_music' : _data['is_music'],
      'is_podcast' : _data['is_podcast'],
      'is_ringtone' : _data['is_ringtone'],
      'is_alarm' : _data['is_alarm'],
      'is_notification' : _data['is_notification'],
      '_id' : super.id

    };
  }

  static fromJson(Map<String, dynamic> parsedJson){
    //return jsonSongs.map<SongInfo>().toList();

        return SongInfo._(parsedJson);

    // data['album_id'] = parsedJson['album_id'];
    // data['artist_id'] = parsedJson['artist_id'];
    // data['artist'] = parsedJson['artist'];
    // data['album'] = parsedJson['album'];
    // data['genre_name'] = parsedJson['genre_name'];
    // data['title'] = parsedJson['title'];
    // data['_display_name'] = parsedJson['_display_name'];
    // data['composer'] = parsedJson['composer'];
    // data['year'] = parsedJson['year'];
    // data['track'] = parsedJson['track'];
    // data['duration'] = parsedJson['duration'];
    // data['bookmark'] = parsedJson['bookmark'];
    // data['_data'] = parsedJson['parsedJson'];
    // data['uri'] = parsedJson['uri'];
    // data['_size'] = parsedJson['_size'];
    // data['album_artwork'] = parsedJson['album_artwork'];
    // data['is_music'] = parsedJson['is_music'];
    // data['is_podcast'] = parsedJson['is_podcast'];
    // data['is_ringtone'] = parsedJson['is_ringtone'];
    // data['is_alarm'] = parsedJson['is_alarm'];
    // data['is_notification'] = parsedJson['is_notification'];


    // return SongInfo(
    //     album_id : parsedJson['album_id'],
    //     artist_id : parsedJson['artist_id'],
    //     artist : parsedJson['artist'],
    //     album : parsedJson['album'],
    //     genre_name : parsedJson['genre_name'],
    //     title : parsedJson['title'],
    //     _display_name : parsedJson['_display_name'],
    //     composer : parsedJson['composer'],
    //     year : parsedJson['year'],
    //     track : parsedJson['track'],
    //     duration : parsedJson['duration'],
    //     bookmark : parsedJson['bookmark'],
    //     _data : parsedJson['parsedJson'],
    //     uri : parsedJson['uri'],
    //     _size : parsedJson['_size'],
    //     album_artwork : parsedJson['album_artwork'],
    //     is_music : parsedJson['is_music'],
    //     is_podcast : parsedJson['is_podcast'],
    //     is_ringtone : parsedJson['is_ringtone'],
    //     is_alarm : parsedJson['is_alarm'],
    //     is_notification : parsedJson['is_notification']
    // );
  }

  /// Returns the album id which this song appears.
  String get albumId => _data['album_id'];

  /// Returns the artist id who create this audio file.
  String get artistId => _data['artist_id'];

  /// Returns the artist name who create this audio file.
  String get artist => _data['artist'];

  /// Returns the album title which this song appears.
  String get album => _data['album'];

  // Returns the genre name which this song belongs.
  //String get genre => _data['genre_name'];

  /// Returns the song title.
  String get title => _data['title'];

  /// Returns the song display name. Display name string
  /// is a combination of [Track number] + [Song title] [File extension]
  /// Something like 1 My pretty song.mp3
  String get displayName => _data['_display_name'];

  /// Returns the composer name of this song.
  String get composer => _data['composer'];

  /// Returns the year of this song was created.
  String get year => _data['year'];

  /// Returns the album track number if this song has one.
  String get track => _data['track'];

  /// Returns a String with a number in milliseconds (ms) that is the duration of this audio file.
  String get duration => _data['duration'];

  /// Returns in ms, playback position when this song was stopped.
  /// from the last time.
  String get bookmark => _data['bookmark'];

  /// Returns a String with a file path to audio data file
  String get filePath => _data['_data'];

  String get uri => _data["uri"];

  /// Returns a String with the size, in bytes, of this audio file.
  String get fileSize => _data['_size'];

  ///Returns album artwork path which current song appears.
  String get albumArtwork => _data['album_artwork'];

  bool get isMusic => _data['is_music'];

  bool get isPodcast => _data['is_podcast'];

  bool get isRingtone => _data['is_ringtone'];

  bool get isAlarm => _data['is_alarm'];

  bool get isNotification => _data['is_notification'];
}
