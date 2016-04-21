package com.afollestad.aidlexamplereceiver;

import bean.MessageBean;

public class PostEvent
{
  private static final String TAG = "PostEvent";
  public MessageBean msg;
  
  public PostEvent(MessageBean msg)
  {
    this.msg=msg;
  }
}
