The sample project for recruitation for a backend developer at INFORM GmbH.

It consists of 2 projects:
Backend, located in this repo,
Web Frontend located at https://github.com/marcel-makowski/informtechtask-frontend

Some remarks:
- The general idea is a database app for the collection of whatever devices (for example old gameboys). It allows to track the state of every device and track repairs (time and cost-wise) on them.
- ASIDE from CRUD operations, one can start and stop the timer on the repair to track time spent on the fix. The logic is controlled by the backend.
- My first time using:
  * Spring and Spring Boot
  * H2 DB
  * Angular
  * typescript
  * various API testing tools
  * first time in years and years doing any web development with HTML and CSS
- The backend is organized with hexagonal erchitecture as requested.
- The architecture of the frontend is basically not existing. In general it is a bit wonky but it works.
- To save on time and focus on the important things, I ignored testing, localization, UI visual fidelity.
- I learned a lot, and really tested my ability to find solution for any challenge. For example, I had major issues with requests being blocked on my local machine by the CORS mechanism, which til recently I had no idea even existed.
- I am happy to have achieved a state, where I could use the system to track my gameboy collection. I think that's a nice thing, considering the state of my knowledge before attempting this challenge.
