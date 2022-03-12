from https://redux.js.org/usage/writing-tests

Action Creators & Thunks​

In Redux, action creators are functions which return plain objects. Our recommendation is not to write action creators manually, but instead have them generated automatically by createSlice, or created via createAction from @reduxjs/toolkit. As such, you should not feel the need to test action creators by themselves (the Redux Toolkit maintainers have already done that for you!).

The return value of action creators is considered an implementation detail within your application, and when following an integration testing style, do not need explicit tests.

Similarly for thunks using Redux Thunk, our recommendation is not to write them manually, but instead use createAsyncThunk from @reduxjs/toolkit. The thunk handles dispatching the appropriate pending, fulfilled and rejected action types for you based on the lifecycle of the thunk.

We consider thunk behavior to be an implementation detail of the application, and recommend that it be covered by testing the group of components (or whole app) using it, rather than testing the thunk in isolation.
