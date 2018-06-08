/*
 * Copyright (c) 2018. NowBoarding Pty Ltd
 */

package com.fancylab.citylistdemo.application;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

public class FakeTestRunner extends AndroidJUnitRunner {

  @Override
  public Application newApplication(ClassLoader cl, String className, Context context)
      throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return super.newApplication(cl, TestAppApplication.class.getName(), context);
  }
}