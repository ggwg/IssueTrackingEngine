# IssueTrackingEngine
Java Issue Tracking Engine - Backend functionality for a task board, replicating features such as Trello or Jira. With Test Suite.

# Classes Overview

**IssueTrackingEngine** – The interface for interacting with an instance of an issue tracking engine. I chose to
implement the Issue Tracking Engine as an object rather than a class of static methods since there may be
more than one instance of an issue tracking engine at one time. This is also the reason I chose to use Java, as
this would allow a scalable object-orientated approach.

**Issue** – A class describing an instance of an issue. Contains fields storing information such as title, unique id,
creation date, current state, comments, IDs of users, and a history of state changes and comments.
User – A class containing information on a single user. Contains fields storing the name and unique ID of the
user.

**Utils/State** – Enum class containing the 3 states: TODO, IN_PROGRESS and DONE.

**Utils/StateChange** – Represents a single state change. Each state change records the new state, a string
comment and a timestamp. Each issue has a list of StateChanges.

**Utils/Comment** – Comment object which stores a string message and string timestamp (recording the time
the message was written). Each issue has a list of comments.

**UniqueIDGenerator/ IssueIDGenerator, UserIDGenerator** – Thread-safe implementation of a
unique ID generator for each issue and each user, respectively. Each class uses a singleton structure to ensure
that only 1 instance of each object can be created, so generated IDs are always unique. Upon each call to
generateUniqueID(), the internal unique ID is incremented and returned, starting with an initial ID of 1.
Given more time, I would probably have used an interface for the 2 related classes or reduced duplication by
combining the 2 classes into 1 singleton class.

**Main** – Quick implementation and demonstration of test scenario 1 outlined in the spec. Mainly written to
provide you with a very simple example of my Issue Tracking Engine implementation.

# Testing

A test-driven development approach was used for the Issue Tracking Engine project. Tests were conducted
alongside the implementation of each subtask outlined in the design brief. The test suite implemented can be found in test/java/IssueTrackingEngineTest.

The following test cases were written:

- addingIssueUpdatesListOfIssues()
- removingIssueUpdatesListOfIssues()
- issueStateChangesCorrectlyRecorded()
- issueStateChangeCommentsCorrectlyRecorded()
- usersHaveBeenCorrectlyAssignedToIssue()
- usersAssignedToIssueHaveBeenRecordedCorrectly()
- addingUserUpdatesListOfUsers()
- removingUserUpdatesListOfUsers()
- getUsersReturnsCorrectListOfUsers()
- getIssuesFiltersBasedOnState()
- getIssuesFiltersBasedOnUserID()
- getIssuesFiltersBasedOnStartDate()
- getIssuesFiltersBasedOnEndDate()
- getIssuesReturnsCorrectListOfIssues()
