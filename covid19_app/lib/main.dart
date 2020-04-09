import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:provider/provider.dart';
import './model/result.dart';
import './screens/HomeScreen.dart';
import './screens/SymptomsScreen.dart';
import './model/symptoms.dart';

/*
Name: Teddy Nyambe 
Project Name: Covid10 ICT Communitry response
Contributers List:
          - 
          -
*/
void main() {
  WidgetsFlutterBinding.ensureInitialized();
  SystemChrome.setPreferredOrientations(
      [DeviceOrientation.portraitUp, DeviceOrientation.portraitDown]).then((_) {
    runApp(Covid19App());
  });
}

class Covid19App extends StatelessWidget {
  const Covid19App({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider<ManageResult>(create: (ctx) => ManageResult()),
        ChangeNotifierProvider<Symptoms>(
          create: (ctx) => Symptoms(),
        )
      ],
      child: MaterialApp(
        title: 'Covid-19 Reporting App',
        home: HomeScreen(),
        routes: {
          SymptomsScreen.routeName: (ctx) => SymptomsScreen(),
        },
      ),
    );
  }
}
