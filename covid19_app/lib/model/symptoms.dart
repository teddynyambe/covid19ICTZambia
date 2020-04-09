import 'dart:convert';

import 'package:http/http.dart' as http;

import 'package:flutter/material.dart';

class Symptom {
  final fever;
  final soreThroat;
  final muscleJointPain;
  final fatigue;
  final dryCough;
  final headache;
  final shortBreath;
  final runnyNose;

  Symptom({
    @required this.dryCough,
    @required this.fatigue,
    @required this.fever,
    @required this.headache,
    @required this.muscleJointPain,
    @required this.runnyNose,
    @required this.shortBreath,
    @required this.soreThroat,
  });
}

class Symptoms with ChangeNotifier {
  Future<void> reportSymptoms(Symptom symptom) async {
    final url = 'http://covid19app-env.eba-usa5atqm.us-east-2.elasticbeanstalk.com/covid19/symptom/submit?token=1967';
    const Map<String, String> headers = {
      "content-type": "application/json",
      "Accept": "application/json"
    };
    try {
      var response = await http.post(
        url,
        body: json.encode({
          'fever': symptom.fever,
          'soreThroat': symptom.soreThroat,
          'fatigue': symptom.fatigue,
          'dryCough': symptom.dryCough,
          'headache': symptom.headache,
          'muscleJointPain': symptom.muscleJointPain,
          'runnyNose': symptom.runnyNose,
          'shortBreath': symptom.shortBreath,
        }),
        headers: headers,
      );
    } catch (error) {
      throw error;
    }
  }
}
