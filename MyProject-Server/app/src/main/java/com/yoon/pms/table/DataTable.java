package com.yoon.pms.table;

import com.yoon.util.Request;
import com.yoon.util.Response;

public interface DataTable {
  void service(Request request, Response response) throws Exception;
}
