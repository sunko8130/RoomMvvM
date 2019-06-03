package roomdemo.wiseass.com.roomdemo.data;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import android.support.annotation.NonNull;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ListItemDao_Impl implements ListItemDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfListItem;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfListItem;

  public ListItemDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfListItem = new EntityInsertionAdapter<ListItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `ListItem`(`itemId`,`message`,`colorResource`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ListItem value) {
        if (value.getItemId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getItemId());
        }
        if (value.getMessage() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getMessage());
        }
        stmt.bindLong(3, value.getColorResource());
      }
    };
    this.__deletionAdapterOfListItem = new EntityDeletionOrUpdateAdapter<ListItem>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `ListItem` WHERE `itemId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ListItem value) {
        if (value.getItemId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getItemId());
        }
      }
    };
  }

  @Override
  public Long insertListItem(ListItem listItem) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfListItem.insertAndReturnId(listItem);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteListItem(ListItem listItem) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfListItem.handle(listItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<ListItem> getListItemById(String itemId) {
    final String _sql = "SELECT * FROM ListItem WHERE itemId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (itemId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, itemId);
    }
    return new ComputableLiveData<ListItem>() {
      private Observer _observer;

      @Override
      protected ListItem compute() {
        if (_observer == null) {
          _observer = new Observer("ListItem") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfItemId = _cursor.getColumnIndexOrThrow("itemId");
          final int _cursorIndexOfMessage = _cursor.getColumnIndexOrThrow("message");
          final int _cursorIndexOfColorResource = _cursor.getColumnIndexOrThrow("colorResource");
          final ListItem _result;
          if(_cursor.moveToFirst()) {
            final String _tmpItemId;
            _tmpItemId = _cursor.getString(_cursorIndexOfItemId);
            final String _tmpMessage;
            _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            final int _tmpColorResource;
            _tmpColorResource = _cursor.getInt(_cursorIndexOfColorResource);
            _result = new ListItem(_tmpItemId,_tmpMessage,_tmpColorResource);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<List<ListItem>> getListItems() {
    final String _sql = "SELECT * FROM ListItem";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<ListItem>>() {
      private Observer _observer;

      @Override
      protected List<ListItem> compute() {
        if (_observer == null) {
          _observer = new Observer("ListItem") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfItemId = _cursor.getColumnIndexOrThrow("itemId");
          final int _cursorIndexOfMessage = _cursor.getColumnIndexOrThrow("message");
          final int _cursorIndexOfColorResource = _cursor.getColumnIndexOrThrow("colorResource");
          final List<ListItem> _result = new ArrayList<ListItem>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ListItem _item;
            final String _tmpItemId;
            _tmpItemId = _cursor.getString(_cursorIndexOfItemId);
            final String _tmpMessage;
            _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            final int _tmpColorResource;
            _tmpColorResource = _cursor.getInt(_cursorIndexOfColorResource);
            _item = new ListItem(_tmpItemId,_tmpMessage,_tmpColorResource);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }
}
