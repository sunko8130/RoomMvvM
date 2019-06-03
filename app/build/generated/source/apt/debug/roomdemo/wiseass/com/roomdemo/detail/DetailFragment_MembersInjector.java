// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package roomdemo.wiseass.com.roomdemo.detail;

import android.arch.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class DetailFragment_MembersInjector implements MembersInjector<DetailFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public DetailFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<DetailFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new DetailFragment_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(DetailFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  public static void injectViewModelFactory(
      DetailFragment instance, ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
