package plants.temperature.web

import groovy.transform.Canonical;

@Canonical
class Option {
	String value
	String label
}

class StaticOption{
	static public getOptions(){
		return [
			new Option("uno","uno"),
			new Option("due","due"),
			new Option("tre","tre"),
			new Option("quattro","quattro")
		]
	}
}
