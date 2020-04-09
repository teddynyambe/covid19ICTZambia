import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class Result {
  final reportDate;
  final confirmedZambia;
  final confirmedWorld;
  final deathsZambia;
  final deathsWorld;
  final recoveredZambia;
  final recoveredWorld;
  final testedPositive;
  final testedNegative;

  Result({
    @required this.confirmedWorld,
    @required this.confirmedZambia,
    @required this.deathsWorld,
    @required this.deathsZambia,
    @required this.recoveredWorld,
    @required this.recoveredZambia,
    @required this.reportDate,
    @required this.testedNegative,
    @required this.testedPositive,
  });
}

class ManageResult with ChangeNotifier {
  Result _result;

  Result get result{
    return _result;
  }
  Future<void> setResults() async {
    final url =
        'http://covid19app-env.eba-usa5atqm.us-east-2.elasticbeanstalk.com/covid19?token=1967';
    const Map<String, String> header = {
      "content-type": "application/json",
      "Accept": "application/json"
    };

    try {
      var response = await http.get(url, headers: header);
      final extractedResult =
          json.decode(response.body) as Map<String, dynamic>;
      if (extractedResult == null) {
        return;
      }

      // print("Testing: ${extractedResult['confirmedWorld']}, ${extractedResult['confirmedZambia']}");
      _result = Result(
        confirmedWorld: extractedResult['confirmedWorld'],
        confirmedZambia: extractedResult['confirmedZambia'],
        deathsWorld: extractedResult['deathsWorld'],
        deathsZambia: extractedResult['deathsZambia'],
        recoveredWorld: extractedResult['recoveredWorld'],
        recoveredZambia: extractedResult['recoveredZambia'],
        reportDate: extractedResult['reportDate'],
        testedNegative: extractedResult['testedNegative'],
        testedPositive: extractedResult['testedPositive'],
      );
      
      notifyListeners();
      // print(json.decode(response.body));

    } catch (error) {
      throw error;
    }
  }
}
